package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.*;

public class ParticipantSearchPane extends GridPane {

    private TextField textFieldSearch = new TextField();
    private Button buttonSearch = new Button("Find deltager");
    private TextArea textFieldResult = new TextArea();

    public ParticipantSearchPane() {
        this.setPadding(new Insets(10));
        this.setHgap(10); this.setVgap(10);

        this.add(new Label("Søg deltager (navn):"), 0, 0);
        this.add(textFieldSearch, 1, 0);
        this.add(buttonSearch, 2, 0);

        textFieldResult.setEditable(false);
        this.add(textFieldResult, 0, 1, 3, 1);

        buttonSearch.setOnAction(e -> doSearch());
    }

    public void refresh() {
        textFieldSearch.clear();
        textFieldResult.clear();
    }

    private void doSearch() {
        String navn = textFieldSearch.getText().trim();
        if (navn.isEmpty()) return;

        Deltager found = null;
        for (Deltager deltager : Controller.getDeltagere()) {
            if (deltager.getNavn().equalsIgnoreCase(navn)) {
                found = deltager;
                break;
            }
        }
        if (found == null) {
            textFieldResult.setText("Deltager ikke fundet");
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Deltager: ").append(found.getNavn()).append("\n");
        stringBuilder.append("Firma: ").append(found.getFirma() != null ? found.getFirma().getNavn() : "Ingen").append("\n");
        stringBuilder.append("Foredragsholder: ").append(found.isErForedragsholder() ? "Ja" : "Nej").append("\n\n");

        stringBuilder.append("Tilmeldinger:\n");
        for (Tilmelding tilmelding : found.getTilmeldinger()) {
            stringBuilder.append(" - Konference: ").append(tilmelding.getKonference().getNavn()).append("\n");
            stringBuilder.append("   Datoer: ").append(tilmelding.getAnkomstDato()).append(" til ").append(tilmelding.getAfrejseDato()).append("\n");
            stringBuilder.append("   Pris for tilmelding: ").append(tilmelding.getTotalPris()).append(" kr\n");
        }

        double total = found.getTilmeldinger().stream().mapToDouble(Tilmelding::getTotalPris).sum();
        stringBuilder.append("\nSamlet pris for deltager: ").append(total).append(" kr\n");

        textFieldResult.setText(stringBuilder.toString());
    }
}
