package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.*;

public class HotelReservationWindow extends Stage {

    private Tilmelding tilmelding;

    public HotelReservationWindow(Tilmelding tilmelding) {
        this.tilmelding = tilmelding;

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(10); pane.setVgap(10);

        ListView<Hotel> hotelListView = new ListView<>();
        hotelListView.getItems().setAll(tilmelding.getKonference().getHoteller());
        hotelListView.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(Hotel item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNavn());
            }
        });

        CheckBox checkBoxDobbeltVæreslse = new CheckBox("Dobbeltværelse");

        ListView<Service> serviceListView = new ListView<>();
        serviceListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); // tillader valg af flere services
        serviceListView.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(Service item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNavn() + " (" + item.getPris() + " kr)");
            }
        });

        hotelListView.getSelectionModel().selectedItemProperty().addListener( // opdaterere services ved valg af hotel
                (obs, o, n) -> {
                    if (n != null) {
                        serviceListView.getItems().setAll(n.getServices());
                    } else {
                        serviceListView.getItems().clear();
                    }
                }
        );

        // hvis deltager har ledsager → dobbeltværelse automatisk
        if (tilmelding.getDeltager().getLedsager() != null) {
            checkBoxDobbeltVæreslse.setSelected(true);
            checkBoxDobbeltVæreslse.setDisable(true);
        }

        Button buttonCreate = new Button("Lav reservation");

        buttonCreate.setOnAction(e -> {
            Hotel hotel = hotelListView.getSelectionModel().getSelectedItem();
            if (hotel == null) return;

            HotelReservation hotelReservation = tilmelding.createHotelReservation(checkBoxDobbeltVæreslse.isSelected(), hotel);

            for (Service service : serviceListView.getSelectionModel().getSelectedItems()) {
                hotelReservation.addService(service);
            }

            this.close();
        });

        pane.add(new Label("Hoteller:"), 0, 0);
        pane.add(hotelListView, 0, 1);

        pane.add(checkBoxDobbeltVæreslse, 0, 2);

        pane.add(new Label("Services (vælg flere):"), 1, 0);
        pane.add(serviceListView, 1, 1);

        pane.add(buttonCreate, 0, 3);

        this.setScene(new Scene(pane, 600, 400));
        this.setTitle("Hotelreservation");
    }
}
