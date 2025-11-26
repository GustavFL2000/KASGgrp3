package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;

import java.io.FileWriter;
import java.io.IOException;

public class UdflugtOversigtPane extends GridPane {

    private final ListView<Konference> konferenceListView = new ListView<>();
    private final TextArea textAreaOversigt = new TextArea();
    private final Button buttonGemFil = new Button("Gem som fil");

    public UdflugtOversigtPane() {
        setPadding(new Insets(20));
        setHgap(20);
        setVgap(10);

        // Konferenceliste
        konferenceListView.setPrefWidth(200);
        konferenceListView.setItems(javafx.collections.FXCollections.observableList(
                Controller.getKonferencer()
        ));

        konferenceListView.getSelectionModel().selectedItemProperty().addListener(
                (ov, oldVal, newVal) -> updateOversigt()
        );

        add(new Label("Konferencer:"), 0, 0);
        add(konferenceListView, 0, 1);

        // Oversigt
        textAreaOversigt.setEditable(false);
        textAreaOversigt.setPrefWidth(500);
        textAreaOversigt.setPrefHeight(500);

        add(new Label("Udflugtoversigt:"), 1, 0);
        add(textAreaOversigt, 1, 1);

        // Gem-knap
        buttonGemFil.setOnAction(event -> gemFil());
        add(buttonGemFil, 1, 2);
    }

    private void updateOversigt() {
        Konference konference = konferenceListView.getSelectionModel().getSelectedItem();
        if (konference == null) return;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Udflugtoversigt for konference: ")
                .append(konference.getNavn())
                .append("\n\n");

        for (Udflugt u : konference.getUdflugter()) {
            stringBuilder.append("Udflugt: ").append(u.getNavn())
                    .append(" (Pris: ").append(u.getPris()).append(" kr.)\n");

            boolean hasLedsagere = false;

            for (Tilmelding tilmelding : konference.getTilmeldinger()) {
                Deltager deltager = tilmelding.getDeltager();
                if (deltager.getLedsager() != null &&
                        deltager.getLedsager().getUdflugter().contains(u)) {

                    hasLedsagere = true;
                    stringBuilder.append("   - ").append(deltager.getLedsager().getNavn())
                            .append(" (")
                            .append("Deltager: ").append(deltager.getNavn())
                            .append(")\n");
                }
            }

            if (!hasLedsagere) {
                stringBuilder.append("   (Ingen ledsagere tilmeldt)\n");
            }

            stringBuilder.append("\n");
        }

        textAreaOversigt.setText(stringBuilder.toString());
    }

    public void refresh() {
        updateOversigt();
    }


    private void gemFil() {
        Konference konference = konferenceListView.getSelectionModel().getSelectedItem();
        if (konference == null) return;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Gem udflugtoversigt");
        fileChooser.setInitialFileName("udflugt_oversigt.txt");

        var file = fileChooser.showSaveDialog(new Stage());
        if (file == null) return;

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(textAreaOversigt.getText());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
