package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.*;

public class LedsagerPane extends GridPane {

    private ListView<Deltager> deltagerListView = new ListView<>();
    private TextField textFieldLedsagerNavn = new TextField();
    private Button buttonCreateLedsager = new Button("Opret ledsager");
    private ListView<Udflugt> udflugtListView = new ListView<>();
    private Button buttonTilknyUdflugt = new Button("Tilknyt udvalgte udflugter");
    private Label labelStatus = new Label();

    public LedsagerPane() {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);

        this.add(new Label("Vælg deltager:"), 0, 0);
        this.add(deltagerListView, 0, 1);

        this.add(new Label("Ledsager navn:"), 1, 0);
        this.add(textFieldLedsagerNavn, 1, 1);
        this.add(buttonCreateLedsager, 1, 2);

        this.add(new Label("Udflugter (vælg konference først):"), 2, 0);
        this.add(udflugtListView, 2, 1);
        this.add(buttonTilknyUdflugt, 2, 2);
        this.add(labelStatus, 0, 5, 2, 1);
        deltagerListView.getSelectionModel().selectedItemProperty().addListener((obs, o, n) -> updateUdflugter());
        buttonCreateLedsager.setOnAction(e -> createLedsager());
        buttonTilknyUdflugt.setOnAction(e -> tilknytUdflugter());

        refresh();
    }

    public void refresh() {
        deltagerListView.getItems().setAll(Controller.getDeltagere());
        deltagerListView.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
            @Override protected void updateItem(Deltager item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNavn());
            }
        });
        udflugtListView.getItems().clear();
    }

    private void updateUdflugter() {
        Deltager deltager = deltagerListView.getSelectionModel().getSelectedItem();
        if (deltager == null) {
            udflugtListView.getItems().clear();
            return;
        }
        //viser udflugter for den konference deltageren er tilmeldt
        if (!deltager.getTilmeldinger().isEmpty()) {
            Konference konference = deltager.getTilmeldinger().get(0).getKonference();
            udflugtListView.getItems().setAll(konference.getUdflugter());
            udflugtListView.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
                @Override protected void updateItem(Udflugt item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.getNavn() + " (" + item.getPris() + " kr)");
                }
            });
        } else {
            udflugtListView.getItems().clear();
        }
    }

    private void createLedsager() {
        Deltager deltager = deltagerListView.getSelectionModel().getSelectedItem();
        if (deltager == null) return;
        String navn = textFieldLedsagerNavn.getText().trim();
        if (navn.isEmpty()) return;
        Ledsager ledsager = deltager.createLedsager(navn);
        labelStatus.setText("Ledsager oprettet ved navnet:  " + navn);
        textFieldLedsagerNavn.clear();
        refresh();
    }

    private void tilknytUdflugter() {
        Deltager deltager = deltagerListView.getSelectionModel().getSelectedItem();
        if (deltager == null || deltager.getLedsager() == null) {
            labelStatus.setText("Vælg en deltager med en ledsager.");
            return;
        }

        Ledsager ledsager = deltager.getLedsager();

        for (Udflugt udflugt : udflugtListView.getSelectionModel().getSelectedItems()) {
            ledsager.addUdflugt(udflugt);
        }

        labelStatus.setText("Udflugter tilknyttet ledsageren: " + ledsager.getNavn());
    }

}
