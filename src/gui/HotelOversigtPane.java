package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HotelOversigtPane extends BorderPane {

    private ListView<Konference> konferenceListView = new ListView<>();
    private TextArea textArea = new TextArea();
    private Button buttonGemFil = new Button("Gem til fil");

    // Constructor
    public HotelOversigtPane() {
        this.setPadding(new Insets(10));
        konferenceListView.getItems().setAll(Controller.getKonferencer());
        konferenceListView.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(Konference item, boolean empty) { // viser kun konference navn
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNavn());
            }
        });

        // Layout
        VBox left = new VBox(6, new Label("Konferencer:"), konferenceListView);
        left.setPadding(new Insets(6));
        this.setLeft(left);

        textArea.setEditable(false);
        this.setCenter(textArea);

        VBox bottom = new VBox(6, buttonGemFil);
        bottom.setPadding(new Insets(6));
        this.setBottom(bottom);

        konferenceListView.getSelectionModel().selectedItemProperty().addListener((obs, o, n) -> refresh()); // opdaterer tekstområdet ved valg af konference

        buttonGemFil.setOnAction(e -> { // gemmer tekstområdet til fil
            Konference k = konferenceListView.getSelectionModel().getSelectedItem();
            if (k == null) return;
            try {
                Path p = Path.of("hoteloversigt_" + k.getNavn().replaceAll("\\s+","_") + ".txt");
                Files.writeString(p, textArea.getText());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        if (!konferenceListView.getItems().isEmpty()) konferenceListView.getSelectionModel().select(0);
        refresh();
    }

    public void refresh() { // opdtarer tekstområdet
        Konference konference = konferenceListView.getSelectionModel().getSelectedItem();
        if (konference == null) {
            textArea.clear();
            return;
        }
        StringBuilder stringBuilder = new StringBuilder(); //brugt istedet for normal string med += da det er pænere
        for (Hotel hotel : konference.getHoteller()) {
            stringBuilder.append("HOTEL: ").append(hotel.getNavn()).append("\n");
            // liste over deltagere med reservation på dette hotel
            for (Tilmelding tilmelding : konference.getTilmeldinger()) {
                if (tilmelding.getHotelReservation() != null && tilmelding.getHotelReservation().getHotel() == hotel) {
                    Deltager deltager = tilmelding.getDeltager();
                    stringBuilder.append("  - ").append(deltager.getNavn());
                    if (deltager.getLedsager() != null) {
                        stringBuilder.append(" + Ledsager: ").append(deltager.getLedsager().getNavn());
                    }
                    stringBuilder.append(" | Services: ");
                    if (tilmelding.getHotelReservation().getServices().isEmpty()) {
                        stringBuilder.append("Ingen");
                    } else {
                        boolean first = true;
                        for (Service service : tilmelding.getHotelReservation().getServices()) {
                            if (!first) stringBuilder.append(", ");
                            stringBuilder.append(service.getNavn());
                            first = false;
                        }
                    }
                    stringBuilder.append("\n");
                }
            }
            stringBuilder.append("\n");
        }
        textArea.setText(stringBuilder.toString());
    }
}
