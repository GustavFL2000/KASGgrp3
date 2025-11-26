package gui;

import controller.Demo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Gui extends Application {

    private ConferenceOverviewPane conferencePane;
    private ParticipantPane participantPane;
    private CreateTilmeldingPane tilmeldingPane;
    private LedsagerPane ledsagerPane;
    private UdflugtOversigtPane udflugtOversigtPane;
    private HotelOversigtPane hotelOversigtPane;
    private ParticipantSearchPane participantSearchPane;
    private ConferenceParticipantsPane conferenceParticipantsPane;

    @Override
    public void start(Stage stage) {
        Demo.setupTestData();
        stage.setTitle("KAS Konference System");

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));

        // meny venstre side
        VBox menu = new VBox(8);
        menu.setPadding(new Insets(10));

        Button btnKonferencer = new Button("Konferencer");
        Button btnDeltagere = new Button("Deltagere");
        Button btnTilmelding = new Button("Opret tilmelding");
        Button btnLedsager = new Button("Ledsager / Udflugter");
        Button btnUdflugtsOversigt = new Button("Udflugts-oversigt");
        Button btnHotelOversigt = new Button("Hotel-oversigt");
        Button btnSøgDeltager = new Button("Søg deltager");
        Button btnKonfDeltagere = new Button("Deltagere (konf)");

        menu.getChildren().addAll(btnKonferencer, btnDeltagere, btnTilmelding, btnLedsager,
                btnUdflugtsOversigt, btnHotelOversigt, btnSøgDeltager, btnKonfDeltagere);
        borderPane.setLeft(menu);

        // Panerler
        conferencePane = new ConferenceOverviewPane();
        participantPane = new ParticipantPane();
        tilmeldingPane = new CreateTilmeldingPane();
        ledsagerPane = new LedsagerPane();
        udflugtOversigtPane = new UdflugtOversigtPane();
        hotelOversigtPane = new HotelOversigtPane();
        participantSearchPane = new ParticipantSearchPane();
        conferenceParticipantsPane = new ConferenceParticipantsPane();

        // Default view
        borderPane.setCenter(conferencePane);

        // menu knapper
        btnKonferencer.setOnAction(e -> { conferencePane.refresh(); borderPane.setCenter(conferencePane); });
        btnDeltagere.setOnAction(e -> { participantPane.refresh(); borderPane.setCenter(participantPane); });
        btnTilmelding.setOnAction(e -> { tilmeldingPane.refreshAll(); borderPane.setCenter(tilmeldingPane); });
        btnLedsager.setOnAction(e -> { ledsagerPane.refresh(); borderPane.setCenter(ledsagerPane); });
        btnUdflugtsOversigt.setOnAction(e -> { udflugtOversigtPane.refresh(); borderPane.setCenter(udflugtOversigtPane); });
        btnHotelOversigt.setOnAction(e -> { hotelOversigtPane.refresh(); borderPane.setCenter(hotelOversigtPane); });
        btnSøgDeltager.setOnAction(e -> { participantSearchPane.refresh(); borderPane.setCenter(participantSearchPane); });
        btnKonfDeltagere.setOnAction(e -> { conferenceParticipantsPane.refresh(); borderPane.setCenter(conferenceParticipantsPane); });

        Scene scene = new Scene(borderPane, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }
}
