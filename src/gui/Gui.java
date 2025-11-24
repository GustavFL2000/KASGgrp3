package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Gui extends Application {

    public void start(Stage stage) {
        stage.setTitle("Gui Demo 1");
        GridPane pane = new GridPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(true);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        Label lblName = new Label("Name:");
        pane.add(lblName, 0, 0);
        TextField txfName = new TextField();
        pane.add(txfName, 1, 0, 2, 1);

        Button btnUpperCase = new Button("Upper Case");
        pane.add(btnUpperCase, 1, 1);
        GridPane.setMargin(btnUpperCase, new Insets(10, 10, 0, 10));

        Button btnLowerCase = new Button("Lower Case");
        pane.add(btnLowerCase, 2, 1);
        GridPane.setMargin(btnLowerCase, new Insets(10, 10, 0, 10));

    }
}
