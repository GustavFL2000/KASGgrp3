package controller;

import model.*;

import java.time.LocalDate;

public class Demo {

    public static void main(String[] args) {

        setupTestData();

        System.out.println("--- Starter Prisberegning Demonstration ---\n");


        // Scenearie 1 – deltager med ledsager, hotel og services

        System.out.println("--- SCENARIE 1 ---");

        // Find konference
        Konference havOgHimmel = Controller.getKonferenceByNavn("Hav og Himmel");

        // Find hotel
        Hotel denHvideSvane = Controller.getHotelByNavn(havOgHimmel, "Den Hvide Svane");

        // Find service
        Service wifi = null;
        for (Service service : denHvideSvane.getServices()) {
            if (service.getNavn().equals("WIFI")) {
                wifi = service;
                break;
            }
        }

        // Find udflugter
        Udflugt egeskov = Controller.getUdflugtByNavn(havOgHimmel, "Egeskov");
        Udflugt byrundtur = Controller.getUdflugtByNavn(havOgHimmel,
                "Byrundtur i Odense inkl. Frokost");

        // Lav deltager via Controller
        Deltager finn = Controller.createDeltager(
                "Finn", "Egelunden 2", "DK", "1234", "finn@email.dk", false
        );

        // Ledsager
        Ledsager anja = finn.createLedsager("Anja");

        // Tilmelding via Controller
        Tilmelding til1 = Controller.createTilmeldingForKonference(
                3,
                havOgHimmel.getStartDato(),
                havOgHimmel.getSlutDato(),
                havOgHimmel,
                finn
        );

        // Hotelreservation
        HotelReservation res1 = til1.createHotelReservation(true, denHvideSvane);
        res1.addService(wifi);

        // Ledsagers udflugter
        anja.addUdflugt(egeskov);
        anja.addUdflugt(byrundtur);

        // Prisberegning
        double konferencePris = havOgHimmel.getDagsPris() * 3;
        double hotelPris = denHvideSvane.getPrisDobbelt() * 2;
        double servicePris = wifi.getPris()*2;
        double udflugtPris = egeskov.getPris() + byrundtur.getPris();

        double expected1 = konferencePris + hotelPris + servicePris + udflugtPris;
        double actual1 = til1.getTotalPris();

        System.out.println("Forventet: " + expected1);
        System.out.println("Beregnet:  " + actual1);
        System.out.println(Math.abs(expected1 - actual1) < 0.01 ? "RESULTAT: KORREKT" : "FORKERT");
        System.out.println();


        // Scenrie 2 – foredragsholder med hotel

        System.out.println("--- SCENARIE 2 ---");

        Hotel hotelPhoenix = Controller.getHotelByNavn(havOgHimmel, "Hotel Phønix");

        Deltager mette = Controller.createDeltager(
                "Mette", "Vej 1", "DK", "4321", "mette@email.dk", true
        );

        Tilmelding til2 = Controller.createTilmeldingForKonference(
                3,
                havOgHimmel.getStartDato(),
                havOgHimmel.getSlutDato(),
                havOgHimmel,
                mette
        );

        til2.createHotelReservation(false, hotelPhoenix);

        double expected2 = hotelPhoenix.getPrisEnkelt() * 2;
        double actual2 = til2.getTotalPris();

        System.out.println("Forventet: " + expected2);
        System.out.println("Beregnet:  " + actual2);
        System.out.println(Math.abs(expected2 - actual2) < 0.01 ? "RESULTAT: KORREKT" : "FORKERT");
        System.out.println();


        // Scenarie 3 – deltager uden hotel og uden ledsager

        System.out.println("--- SCENARIE 3 ---");

        Deltager keld = Controller.createDeltager(
                "Keld", "Gade 3", "DK", "5555", "keld@email.dk", false
        );

        Tilmelding til3 = Controller.createTilmeldingForKonference(
                3,
                havOgHimmel.getStartDato(),
                havOgHimmel.getSlutDato(),
                havOgHimmel,
                keld
        );

        double expected3 = havOgHimmel.getDagsPris() * 3;
        double actual3 = til3.getTotalPris();

        System.out.println("Forventet: " + expected3);
        System.out.println("Beregnet:  " + actual3);
        System.out.println(Math.abs(expected3 - actual3) < 0.01 ? "RESULTAT: KORREKT" : "FORKERT");
    }

    // Testdata via Controller
    public static void setupTestData() {

        Administrator admin = Controller.createAdministrator("Admin Navn");

        Konference havOgHimmel = Controller.createKonference(
                "Hav og Himmel",
                "Odense",
                LocalDate.of(2025, 11, 28),
                LocalDate.of(2025, 11, 30),
                1500,
                admin
        );

        Konference Atlanta = Controller.createKonference(
                "Atlanata",
                "Aarhus",
                LocalDate.of(2025, 11, 28),
                LocalDate.of(2025, 11, 30),
                1500,
                admin
        );

        Deltager mette = Controller.createDeltager(
                "Mette", "Vej 1", "DK", "4321", "mette@email.dk", false
        );

        Deltager patrickMortensen = Controller.createDeltager(
                "patrick Mortensen", "Vej 2", "DK", "43211234", "patrick@email.dk", true
        );

        Hotel denHvideSvane = Controller.createHotel(
                "Den Hvide Svane", "Overgade 25", "...", 1050, 1250
        );
        denHvideSvane.createService("WIFI", 50);

        Hotel casinoRoyal = Controller.createHotel(
                "Casino Royal", "Domkirken", "...", 20000, 40000
        );
        casinoRoyal.createService("WIFI", 150);
        casinoRoyal.createService("Morgenmad", 250);
        casinoRoyal.createService("Spa adgang", 500);


        Hotel hotelPhoenix = Controller.createHotel(
                "Hotel Phønix", "Vestergade 75", "...", 700, 800
        );
        hotelPhoenix.createService("Bad", 200);
        hotelPhoenix.createService("WIFI", 75);

        Hotel tusind = Controller.createHotel(
                "Pension Tusindfryd", "Store Glasvej 4", "...", 500, 600
        );
        tusind.createService("Morgenmad", 100);

        havOgHimmel.addHotel(denHvideSvane);
        havOgHimmel.addHotel(hotelPhoenix);
        havOgHimmel.addHotel(tusind);

        Atlanta.addHotel(casinoRoyal);


        Controller.createUdflugtForKonference(
                havOgHimmel, "Byrundtur i Odense inkl. Frokost",
                125.0, LocalDate.of(2025, 11, 28)
        );

        Controller.createUdflugtForKonference(
                havOgHimmel, "Egeskov",
                75.0, LocalDate.of(2025, 11, 29)
        );

        Controller.createUdflugtForKonference(
                Atlanta, "Frokost på Aros",
                200.0, LocalDate.of(2025, 11, 30)
        );

        Controller.createUdflugtForKonference(
                Atlanta, "Tur på CeresPark Vejlby",
                125.0, LocalDate.of(2025, 11, 28)
        );

        Controller.createUdflugtForKonference(
                Atlanta, "Party i latinerkvarteret",
                75.0, LocalDate.of(2025, 11, 29)
        );

        Controller.createUdflugtForKonference(
                havOgHimmel, "Trapholt Museum, Kolding inkl. Frokost",
                200.0, LocalDate.of(2025, 11, 30)
        );

        System.out.println("Testdata oprettet.\n");
    }
}
