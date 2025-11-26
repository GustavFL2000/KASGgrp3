package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import model.*;

public class ConferenceOverviewPane extends GridPane {

    private ListView<Konference> konferenceListView = new ListView<>();
    private ListView<Hotel> hotelListView = new ListView<>();
    private ListView<Udflugt> udflugtListView = new ListView<>();
    private ListView<Tilmelding> tilmeldingListView = new ListView<>();

    public ConferenceOverviewPane() {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);

        this.add(new Label("Konferencer:"), 0, 0);
        konferenceListView.getItems().setAll(Controller.getKonferencer());
        setCellFactories();
        this.add(konferenceListView, 0, 1);

        this.add(new Label("Hoteller:"), 1, 0);
        this.add(hotelListView, 1, 1);

        this.add(new Label("Udflugter:"), 2, 0);
        this.add(udflugtListView, 2, 1);

        this.add(new Label("Tilmeldinger:"), 3, 0);
        this.add(tilmeldingListView, 3, 1);

        konferenceListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> updateLists()
        );
    }

    private void setCellFactories() { //sætter hvordan elementerne i listerne skal vises
        konferenceListView.setCellFactory(lv -> new ListCell<>() { //
            @Override protected void updateItem(Konference item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNavn());
            }
        });
        hotelListView.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(Hotel item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNavn());
            }
        });
        udflugtListView.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(Udflugt item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNavn() + " - " + item.getPris() + " kr");
            }
        });
        tilmeldingListView.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(Tilmelding item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getDeltager().getNavn() + " - " + item.getTotalPris() + " kr");
            }
        });
    }

    public void refresh() { //opdaterer konferencelisten og rydder de andre lister
        konferenceListView.getItems().setAll(Controller.getKonferencer());
        hotelListView.getItems().clear();
        udflugtListView.getItems().clear();
        tilmeldingListView.getItems().clear();
    }

    private void updateLists() { //opdaterer de tre lister baseret på den valgte konference
        Konference konference = konferenceListView.getSelectionModel().getSelectedItem();
        if (konference != null) {
            hotelListView.getItems().setAll(konference.getHoteller());
            udflugtListView.getItems().setAll(konference.getUdflugter());
            tilmeldingListView.getItems().setAll(konference.getTilmeldinger());
        } else {
            hotelListView.getItems().clear();
            udflugtListView.getItems().clear();
            tilmeldingListView.getItems().clear();
        }
    }
}
