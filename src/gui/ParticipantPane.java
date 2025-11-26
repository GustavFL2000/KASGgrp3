package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Deltager;
import model.Firma;

public class ParticipantPane extends GridPane {

    private TextField textFieldNavn = new TextField();
    private TextField textFieldAdresse = new TextField();
    private TextField textFieldNationalitet = new TextField();
    private TextField textFieldTelefon = new TextField();
    private TextField textFieldEmail = new TextField();
    private CheckBox comboBoxForedragsHolder = new CheckBox("Foredragsholder?");
    private TextField textFieldFirma = new TextField();
    private ListView<Deltager> deltagerListView = new ListView<>();

    public ParticipantPane() {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);

        this.add(new Label("Navn:"), 0, 0);
        this.add(textFieldNavn, 1, 0);

        this.add(new Label("Adresse:"), 0, 1);
        this.add(textFieldAdresse, 1, 1);

        this.add(new Label("Nationalitet:"), 0, 2);
        this.add(textFieldNationalitet, 1, 2);

        this.add(new Label("Telefon:"), 0, 3);
        this.add(textFieldTelefon, 1, 3);

        this.add(new Label("Email:"), 0, 4);
        this.add(textFieldEmail, 1, 4);

        this.add(comboBoxForedragsHolder, 1, 5);

        this.add(new Label("Firma (valgfrit):"), 0, 6);
        this.add(textFieldFirma, 1, 6);

        Button btnCreate = new Button("Opret deltager");
        this.add(btnCreate, 1, 7);

        this.add(new Label("Deltagere:"), 0, 8);
        this.add(deltagerListView, 1, 8);

        btnCreate.setOnAction(e -> createDeltager());
        refresh();
    }

    private void createDeltager() {
        String firmaNavn = textFieldFirma.getText().trim();
        if (firmaNavn.isEmpty()) {
            Controller.createDeltager(
                    textFieldNavn.getText(),
                    textFieldAdresse.getText(),
                    textFieldNationalitet.getText(),
                    textFieldTelefon.getText(),
                    textFieldEmail.getText(),
                    comboBoxForedragsHolder.isSelected()
            );
        } else {
            Firma firma = Controller.createFirma(firmaNavn, "");
            Controller.createDeltager(
                    textFieldNavn.getText(),
                    textFieldAdresse.getText(),
                    textFieldNationalitet.getText(),
                    textFieldTelefon.getText(),
                    textFieldEmail.getText(),
                    comboBoxForedragsHolder.isSelected(),
                    firma
            );
        }
        clearForm();
        refresh();
    }

    private void clearForm() {
        textFieldNavn.clear();
        textFieldAdresse.clear();
        textFieldNationalitet.clear();
        textFieldTelefon.clear();
        textFieldEmail.clear();
        textFieldFirma.clear();
        comboBoxForedragsHolder.setSelected(false);
    }

    public void refresh() {
        deltagerListView.getItems().setAll(Controller.getDeltagere());
        deltagerListView.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(Deltager item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNavn());
            }
        });
    }
}
