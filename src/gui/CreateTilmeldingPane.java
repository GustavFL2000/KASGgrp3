package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.*;

import java.time.LocalDate;

public class CreateTilmeldingPane extends GridPane {

    private ListView<Deltager> deltagerListView = new ListView<>();
    private ListView<Konference> konferenceListView = new ListView<>();

    private DatePicker dpAnkomst = new DatePicker();
    private DatePicker dpAfrejse = new DatePicker();

    private Button btnOpretTilmelding = new Button("Opret tilmelding");
    private Button btnHotelReservation = new Button("Hotelreservation");

    private Label labelStatus = new Label();
    private Tilmelding lastTilmelding;

    public CreateTilmeldingPane() {
        this.setPadding(new Insets(10));
        this.setHgap(10); this.setVgap(10);

        this.add(new Label("Deltagere:"), 0, 0);
        this.add(deltagerListView, 0, 1);

        this.add(new Label("Konferencer:"), 1, 0);
        this.add(konferenceListView, 1, 1);

        this.add(new Label("Ankomst:"), 0, 2);
        this.add(dpAnkomst, 1, 2);

        this.add(new Label("Afrejse:"), 0, 3);
        this.add(dpAfrejse, 1, 3);

        this.add(btnOpretTilmelding, 0, 4);
        this.add(btnHotelReservation, 1, 4);

        this.add(labelStatus, 0, 5, 2, 1);

        btnOpretTilmelding.setOnAction(e -> createTilmelding());
        btnHotelReservation.setOnAction(e -> openHotelReservation());

        refreshAll();
    }

    public void refreshAll() {
        deltagerListView.getItems().setAll(Controller.getDeltagere());
        deltagerListView.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
            @Override protected void updateItem(Deltager item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNavn());
            }
        });

        konferenceListView.getItems().setAll(Controller.getKonferencer());
        konferenceListView.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
            @Override protected void updateItem(Konference item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNavn());
            }
        });
    }

    private void createTilmelding() {
        Deltager deltager = deltagerListView.getSelectionModel().getSelectedItem();
        Konference konference = konferenceListView.getSelectionModel().getSelectedItem();

        if (deltager == null || konference == null) {
            labelStatus.setText("Vælg deltager og konference!");
            return;
        }

        LocalDate ankomst = dpAnkomst.getValue();
        LocalDate afrejse = dpAfrejse.getValue();
        if (ankomst == null || afrejse == null) {
            labelStatus.setText("Vælg ankomst og afrejse!");
            return;
        }
        if (!afrejse.isAfter(ankomst)) {
            labelStatus.setText("Afrejse skal være efter ankomst!");
            return;
        }

        int antalDage = afrejse.getDayOfYear() - ankomst.getDayOfYear();

        lastTilmelding = Controller.createTilmeldingForKonference(
                antalDage, ankomst, afrejse, konference, deltager
        );

        labelStatus.setText("Tilmelding oprettet til " + deltager.getNavn());
    }
    // Åbner hotel reservations vindue
    private void openHotelReservation() {
        if (lastTilmelding == null) {
            labelStatus.setText("Opret en tilmelding først!");
            return;
        }

        new HotelReservationWindow(lastTilmelding).show();
    }
}
