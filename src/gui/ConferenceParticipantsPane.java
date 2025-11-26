package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ConferenceParticipantsPane extends GridPane {

    private ListView<Konference> konferenceListView = new ListView<>();
    private ListView<String> stringListView = new ListView<>();

    public ConferenceParticipantsPane() {
        this.setPadding(new Insets(10));
        this.setHgap(10); this.setVgap(10);

        konferenceListView.getItems().setAll(Controller.getKonferencer());
        konferenceListView.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(Konference item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNavn());
            }
        });

        this.add(new Label("Konferencer:"), 0, 0);
        this.add(konferenceListView, 0, 1);

        this.add(new Label("Deltagere (alfabetisk):"), 1, 0);
        this.add(stringListView, 1, 1);

        konferenceListView.getSelectionModel().selectedItemProperty().addListener((obs, o, n) -> refresh());
        if (!konferenceListView.getItems().isEmpty()) konferenceListView.getSelectionModel().select(0);
        refresh();
    }

    public void refresh() {
        Konference konference = konferenceListView.getSelectionModel().getSelectedItem();
        stringListView.getItems().clear();
        if (konference == null) return;

        List<String> names = new ArrayList<>();
        for (Tilmelding tilmelding : konference.getTilmeldinger()) {
            names.add(tilmelding.getDeltager().getNavn());
        }
        names.sort(Comparator.naturalOrder());
        stringListView.getItems().setAll(names);
    }
}
