import controller.Controller;
import model.*;
import storage.Storage;

import java.time.LocalDate;

public class Demo {

    public static void main(String[] args) {
        setupTestData();

        System.out.println("--- Starter Refaktoriseret Prisberegning Demonstration ---");
        System.out.println();

        // --- SCENARIO 1: Standard deltager med alt ---
        System.out.println("--- SCENARIO 1: Standard deltager med ledsager, hotel og services ---");
        {
            // Setup: Få de nødvendige objekter fra storage
            Konference havOgHimmel = Storage.getKonferencer().stream().filter(k -> k.getNavn().equals("Hav og Himmel")).findFirst().get();
            Hotel denHvideSvane = havOgHimmel.getHoteller().stream().filter(h -> h.getNavn().equals("Den Hvide Svane")).findFirst().get();
            Service wifi = denHvideSvane.getServices().stream().filter(s -> s.getNavn().equals("WIFI")).findFirst().get();
            Udflugt egeskov = havOgHimmel.getUdflugter().stream().filter(u -> u.getNavn().equals("Egeskov")).findFirst().get();
            Udflugt byrundtur = havOgHimmel.getUdflugter().stream().filter(u -> u.getNavn().equals("Byrundtur i Odense inkl. Frokost")).findFirst().get();

            // lav deltager og ledsager
            Deltager finn = Deltager.create("Finn", "Egelunden 2", "DK", "1234", "finn@email.dk", false);
            Ledsager anja = finn.createLedsager("Anja");

            // lav tilmelding
            Tilmelding tilmelding = havOgHimmel.createTilmelding(finn, havOgHimmel.getStartDato(), havOgHimmel.getSlutDato(), 3);

            // Arrange
            // lav hotelreservation med services og tilføj udflugter til ledsager
            HotelReservation reservation = tilmelding.createHotelreservation(true, denHvideSvane);
            reservation.addService(wifi);
            anja.addUdflugt(egeskov);
            anja.addUdflugt(byrundtur);

            // Forventet prisberegning
            double konferencePris = havOgHimmel.getDagsPris() * 3;
            double hotelPris = denHvideSvane.getDobbeltVærelsesPris() * 2; // 2 nights
            double servicePris = wifi.getPris();
            double udflugtsPris = egeskov.getPris() + byrundtur.getPris();
            double expectedPrice = konferencePris + hotelPris + servicePris + udflugtsPris;

            // Act
            double actualPrice = tilmelding.getTotalPris();

            // Resultater
            System.out.println("Beregner pris for: " + finn.getNavn());
            System.out.println("  - Konference (3 dage): " + konferencePris + " kr");
            System.out.println("  - Hotel (2 nætter, dobbeltværelse): " + hotelPris + " kr");
            System.out.println("  - Hotelservice (WIFI): " + servicePris + " kr");
            System.out.println("  - Udflugter (Ledsager): " + udflugtsPris + " kr");
            System.out.println("------------------------------------------");
            System.out.println("Forventet Totalpris: " + expectedPrice + " kr");
            System.out.println("Beregnet Totalpris:  " + actualPrice + " kr");
            if (Math.abs(expectedPrice - actualPrice) < 0.01) {
                System.out.println("RESULTAT: KORREKT");
            } else {
                System.out.println("RESULTAT: FORKERT - Forventet: " + expectedPrice + ", Beregnet: " + actualPrice);
            }
        }
        System.out.println();

        // --- SCENARIO 2: Foredragsholder med hotel ---
        System.out.println("--- SCENARIO 2: Foredragsholder med hotel (enkeltværelse) ---");
        {
            // Setup
            Konference havOgHimmel = Storage.getKonferencer().stream().filter(k -> k.getNavn().equals("Hav og Himmel")).findFirst().get();
            Hotel hoetelPhoenix = havOgHimmel.getHoteller().stream().filter(h -> h.getNavn().equals("Hotel Phønix")).findFirst().get();
            
            Deltager mette = Deltager.create("Mette", "Vej 1", "DK", "4321", "mette@email.dk", true);
            
            Tilmelding tilmelding = havOgHimmel.createTilmelding(mette, havOgHimmel.getStartDato(), havOgHimmel.getSlutDato(), 3);

            // Arrange
            tilmelding.createHotelreservation(false, hoetelPhoenix); // false for single room

            // forventet prisberegning
            double konferencePris = 0; // Foredragsholder
            double hotelPris = hoetelPhoenix.getEnkeltVærelsesPris() * 2; // 2 nætter
            double expectedPrice = konferencePris + hotelPris;

            // Act
            double actualPrice = tilmelding.getTotalPris();

            // resultater
            System.out.println("Beregner pris for: " + mette.getNavn() + " (Foredragsholder)");
            System.out.println("  - Konference (3 dage): " + konferencePris + " kr");
            System.out.println("  - Hotel (2 nætter, enkeltværelse): " + hotelPris + " kr");
            System.out.println("------------------------------------------");
            System.out.println("Forventet Totalpris: " + expectedPrice + " kr");
            System.out.println("Beregnet Totalpris:  " + actualPrice + " kr");
            if (Math.abs(expectedPrice - actualPrice) < 0.01) {
                System.out.println("RESULTAT: KORREKT");
            } else {
                System.out.println("RESULTAT: FORKERT - Forventet: " + expectedPrice + ", Beregnet: " + actualPrice);
            }
        }
        System.out.println();

        // --- SCENARIO 3: Deltager uden ledsager eller hotel ---
        System.out.println("--- SCENARIO 3: Deltager uden ledsager eller hotel ---");
        {
            // Setup
            Konference havOgHimmel = Storage.getKonferencer().stream().filter(k -> k.getNavn().equals("Hav og Himmel")).findFirst().get();
            
            Deltager keld = Deltager.create("Keld", "Gade 3", "DK", "5555", "keld@email.dk", false);
            
            Tilmelding tilmelding = havOgHimmel.createTilmelding(keld, havOgHimmel.getStartDato(), havOgHimmel.getSlutDato(), 3);

            // Eforventet prisberegning
            double konferencePris = havOgHimmel.getDagsPris() * 3;
            double expectedPrice = konferencePris;

            // Act
            double actualPrice = tilmelding.getTotalPris();

            // resultater
            System.out.println("Beregner pris for: " + keld.getNavn());
            System.out.println("  - Konference (3 dage): " + konferencePris + " kr");
            System.out.println("------------------------------------------");
            System.out.println("Forventet Totalpris: " + expectedPrice + " kr");
            System.out.println("Beregnet Totalpris:  " + actualPrice + " kr");
            if (Math.abs(expectedPrice - actualPrice) < 0.01) {
                System.out.println("RESULTAT: KORREKT");
            } else {
                System.out.println("RESULTAT: FORKERT - Forventet: " + expectedPrice + ", Beregnet: " + actualPrice);
            }
        }
    }

    public static void setupTestData() {
        Administrator admin = Administrator.create("Admin Navn");

        Konference havOgHimmel = Konference.create("Hav og Himmel", "Odense",
                LocalDate.of(2025, 11, 28), LocalDate.of(2025, 11, 30), 1500, admin);

        Hotel denHvideSvane = Hotel.create("Den Hvide Svane", "Overgade 25", "...", 1050, 1250);
        denHvideSvane.createService("WIFI", 50);

        Hotel hoetelPhoenix = Hotel.create("Hotel Phønix", "Vestergade 75", "...", 700, 800);
        hoetelPhoenix.createService("Bad", 200);
        hoetelPhoenix.createService("WIFI", 75);

        Hotel pensionTusindfryd = Hotel.create("Pension Tusindfryd", "Store Glasvej 4", "...", 500, 600);
        pensionTusindfryd.createService("Morgenmad", 100);

        havOgHimmel.addHotel(denHvideSvane);
        havOgHimmel.addHotel(hoetelPhoenix);
        havOgHimmel.addHotel(pensionTusindfryd);

        havOgHimmel.createUdflugt("Byrundtur i Odense inkl. Frokost", 125.0, LocalDate.of(2025, 11, 28));
        havOgHimmel.createUdflugt("Egeskov", 75.0, LocalDate.of(2025, 11, 29));
        havOgHimmel.createUdflugt("Trapholt Museum, Kolding inkl. frokost", 200.0, LocalDate.of(2025, 11, 30));

        System.out.println("Test data setup complete.");
        System.out.println();
    }
}
