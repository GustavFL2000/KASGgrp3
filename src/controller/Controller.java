package controller;

import model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

    // -------------------------------------------------------------------------
    // Konference

    public static Konference createKonference(String navn, String sted, LocalDate startDato, LocalDate slutDato, int dagsPris, Administrator administrator) {
        Konference konference = new Konference(navn, sted, startDato, slutDato, dagsPris, administrator);
        Storage.addKonference(konference);
        return konference;
    }

    public static void removeKonference(Konference konference) {
        Storage.removeKonference(konference);
    }

    public static ArrayList<Konference> getKonferencer() {
        return Storage.getKonferencer();
    }

    public static void addHotelToKonference(Konference konference, Hotel hotel) {
        konference.addHotel(hotel);
    }

    public static void removeHotelFromKonference(Konference konference, Hotel hotel) {
        konference.removeHotel(hotel);
    }

    // -------------------------------------------------------------------------
    // Administrator

    public static Administrator createAdministrator(String navn) {
        Administrator admin = new Administrator(navn);
        Storage.addAdministrator(admin);
        return admin;
    }

    public static void setAdministratorOfKonference(Konference konference, Administrator admin) {
        konference.setAdministrator(admin);
    }


    // -------------------------------------------------------------------------
    // Deltager

    public static Deltager createDeltager(String navn, String adresse, String nationalitet, String email, String telefon, boolean erForedragsholder) {
        Deltager deltager = new Deltager(navn, adresse, nationalitet, email, telefon, erForedragsholder);
        Storage.addDeltager(deltager);
        return deltager;
    }

    public static void removeDeltager(Deltager deltager) {
        Storage.removeDeltager(deltager);
    }

    public static ArrayList<Deltager> getDeltagere() {
        return Storage.getDeltagere();
    }

    public static void setFirmaOfDeltager(Deltager deltager, Firma firma) {
        deltager.setFirma(firma);
    }

    // -------------------------------------------------------------------------
    // Ledsager

    public static Ledsager createLedsager(Deltager deltager, String navn) {
        Ledsager ledsager = new Ledsager(navn);
        deltager.setLedsager(ledsager);
        return ledsager;
    }

    public static void addUdflugtToLedsager(Ledsager ledsager, Udflugt udflugt) {
        ledsager.addUdflugt(udflugt);
    }


    // -------------------------------------------------------------------------
    // Firma

    public static Firma createFirma(String navn, String telefon) {
        Firma firma = new Firma(navn, telefon);
        Storage.addFirma(firma);
        return firma;
    }

    public static ArrayList<Firma> getFirmaer() {
        return Storage.getFirmaer();
    }

    // -------------------------------------------------------------------------
    // Hotel

    public static Hotel createHotel(String name, String adresse, String beskrivelse) {
        Hotel hotel = new Hotel(name, adresse, beskrivelse);
        Storage.addHotel(hotel);
        return hotel;
    }

    public static ArrayList<Hotel> getHoteller() {
        return Storage.getHoteller();
    }

    // -------------------------------------------------------------------------
    // Service

    public static Service createServiceForHotel(Hotel hotel, String navn, double pris) {
        return hotel.createService(navn, pris);
    }


    // -------------------------------------------------------------------------
    // Tilmelding

    public static Tilmelding createTilmelding(Konference konference, Deltager deltager, LocalDate ankomstDato, LocalDate afrejseDato, int antalDage) {
        Tilmelding tilmelding = new Tilmelding(antalDage, ankomstDato, afrejseDato, konference, deltager);
        Storage.addTilmelding(tilmelding);
        konference.addTilmelding(tilmelding);
        deltager.addTilmelding(tilmelding);
        return tilmelding;
    }

    public static HotelReservation createHotelReservationForTilmelding(Tilmelding tilmelding, Hotel hotel, boolean isDoubleRoom, double pris) {
        HotelReservation reservation = tilmelding.createHotelreservation(isDoubleRoom, pris, hotel, tilmelding);
        hotel.addHotelReservation(reservation);
        return reservation;
    }

    public static ArrayList<Tilmelding> getTilmeldinger() {
        return Storage.getTilmeldinger();
    }

    // -------------------------------------------------------------------------
    // Prisberegning

    public static double calculateTotalPrice(Tilmelding tilmelding) {
        return tilmelding.getTotalPris();
    }

    // -------------------------------------------------------------------------
    // Oversigter

    public static String getParticipantsOverview(Konference konference) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Deltageroversigt for Konference: %s (%s - %s)\n",
                konference.getNavn(), konference.getStartDato(), konference.getSlutDato()));
        sb.append("---------------------------------------------------------------------------------\n");

        ArrayList<Tilmelding> tilmeldinger = new ArrayList<>(konference.getTilmeldinger());
        tilmeldinger.sort((t1, t2) -> t1.getDeltager().getNavn().compareTo(t2.getDeltager().getNavn()));

        for (Tilmelding tilmelding : tilmeldinger) {
            Deltager deltager = tilmelding.getDeltager();
            sb.append(String.format("Deltager: %s (Tlf: %s, Email: %s)\n",
                    deltager.getNavn(), deltager.getTelefon(), deltager.getEmail()));
            if (deltager.getFirma() != null) {
                sb.append(String.format("  Firma: %s (Tlf: %s)\n",
                        deltager.getFirma().getNavn(), deltager.getFirma().getTelefon()));
            }
            sb.append(String.format("  Periode: %s til %s (%d dage)\n",
                    tilmelding.getAnkomstDato(), tilmelding.getAfrejseDato(), tilmelding.getAntalDage()));
            if (deltager.getLedsager() != null) {
                sb.append(String.format("  Ledsager: %s\n", deltager.getLedsager().getNavn()));
                if (!deltager.getLedsager().getUdflugter().isEmpty()) {
                    sb.append("    Udflugter for ledsager:\n");
                    for (Udflugt udflugt : deltager.getLedsager().getUdflugter()) {
                        sb.append(String.format("      - %s (Pris: %.2f kr)\n", udflugt.getNavn(), udflugt.getPris()));
                    }
                }
            }
            if (tilmelding.getHotelReservation() != null) {
                HotelReservation hr = tilmelding.getHotelReservation();
                sb.append(String.format("  Hotel: %s (%s, værelse: %s, pris: %.2f kr)\n",
                        hr.getHotel().getNavn(), hr.getHotel().getAdresse(),
                        hr.isDoubleRoom() ? "Dobbelt" : "Enkelt", hr.getPris()));
                if (!hr.getHotel().getServices().isEmpty()) {
                    sb.append("    Services:\n");
                    for (Service service : hr.getHotel().getServices()) {
                        sb.append(String.format("      - %s (Pris: %.2f kr)\n", service.getNavn(), service.getPris()));
                    }
                }
            }
            sb.append(String.format("  Totalpris for tilmelding: %.2f kr\n", calculateTotalPrice(tilmelding)));
            sb.append("---------------------------------------------------------------------------------\n");
        }
        return sb.toString();
    }

    public static String getExcursionOverview(Konference konference) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Udflugtsoversigt for Konference: %s (%s - %s)\n",
                konference.getNavn(), konference.getStartDato(), konference.getSlutDato()));
        sb.append("---------------------------------------------------------------------------------\n");

        for (Udflugt udflugt : konference.getUdflugter()) {
            sb.append(String.format("Udflugt: %s (Pris: %.2f kr, Tidspunkt: %s)\n",
                    udflugt.getNavn(), udflugt.getPris(), udflugt.getTidspunkt()));
            sb.append("  Deltagende ledsagere:\n");
            boolean foundLedsager = false;
            for (Deltager deltager : Storage.getDeltagere()) { // Need to iterate all deltagere to find their ledsagere
                Ledsager ledsager = deltager.getLedsager();
                if (ledsager != null && ledsager.getUdflugter().contains(udflugt)) {
                    sb.append(String.format("    - %s (Ledsager til: %s)\n", ledsager.getNavn(), deltager.getNavn()));
                    foundLedsager = true;
                }
            }
            if (!foundLedsager) {
                sb.append("    (Ingen ledsagere tilmeldt denne udflugt)\n");
            }
            sb.append("---------------------------------------------------------------------------------\n");
        }
        return sb.toString();
    }

    public static String getHotelOverview(Konference konference) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Hoteloversigt for Konference: %s (%s - %s)\n",
                konference.getNavn(), konference.getStartDato(), konference.getSlutDato()));
        sb.append("---------------------------------------------------------------------------------\n");

        for (Hotel hotel : konference.getHoteller()) {
            sb.append(String.format("Hotel: %s (%s)\n", hotel.getNavn(), hotel.getAdresse()));
            if (!hotel.getServices().isEmpty()) {
                sb.append("  Services:\n");
                for (Service service : hotel.getServices()) {
                    sb.append(String.format("    - %s (Pris: %.2f kr)\n", service.getNavn(), service.getPris()));
                }
            }
            sb.append("  Gæster:\n");
            boolean foundGuest = false;
            for (Tilmelding tilmelding : konference.getTilmeldinger()) {
                HotelReservation hr = tilmelding.getHotelReservation();
                if (hr != null && hr.getHotel().equals(hotel)) {
                    Deltager deltager = tilmelding.getDeltager();
                    sb.append(String.format("    - Deltager: %s (Værelse: %s, Pris: %.2f kr)\n",
                            deltager.getNavn(), hr.isDoubleRoom() ? "Dobbelt" : "Enkelt", hr.getPris()));
                    Ledsager ledsager = deltager.getLedsager();
                    if (ledsager != null && hr.isDoubleRoom()) { // Assuming ledsager only if double room
                        sb.append(String.format("      Ledsager: %s\n", ledsager.getNavn()));
                    }
                    foundGuest = true;
                }
            }
            if (!foundGuest) {
                sb.append("    (Ingen gæster på dette hotel gennem konferencen)\n");
            }
            sb.append("---------------------------------------------------------------------------------\n");
        }
        return sb.toString();
    }

    public static String getParticipantDetails(Deltager deltager) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Detaljer for Deltager: %s\n", deltager.getNavn()));
        sb.append("---------------------------------------------------------------------------------\n");

        if (deltager.getFirma() != null) {
            sb.append(String.format("  Firma: %s (Tlf: %s)\n", deltager.getFirma().getNavn(), deltager.getFirma().getTelefon()));
        }
        if (deltager.getLedsager() != null) {
            sb.append(String.format("  Ledsager: %s\n", deltager.getLedsager().getNavn()));
            if (!deltager.getLedsager().getUdflugter().isEmpty()) {
                sb.append("    Udflugter for ledsager:\n");
                for (Udflugt udflugt : deltager.getLedsager().getUdflugter()) {
                    sb.append(String.format("      - %s (Pris: %.2f kr)\n", udflugt.getNavn(), udflugt.getPris()));
                }
            }
        }

        sb.append("\nTilmeldingshistorik:\n");
        if (deltager.getTilmeldinger().isEmpty()) {
            sb.append("  (Ingen tilmeldinger fundet)\n");
        } else {
            for (Tilmelding tilmelding : deltager.getTilmeldinger()) {
                sb.append(String.format("  Konference: %s (%s - %s)\n",
                        tilmelding.getKonference().getNavn(), tilmelding.getKonference().getStartDato(), tilmelding.getKonference().getSlutDato()));
                sb.append(String.format("    Periode: %s til %s (%d dage)\n",
                        tilmelding.getAnkomstDato(), tilmelding.getAfrejseDato(), tilmelding.getAntalDage()));
                if (tilmelding.getHotelReservation() != null) {
                    HotelReservation hr = tilmelding.getHotelReservation();
                    sb.append(String.format("    Hotel: %s (%s, værelse: %s, pris: %.2f kr)\n",
                            hr.getHotel().getNavn(), hr.getHotel().getAdresse(),
                            hr.isDoubleRoom() ? "Dobbelt" : "Enkelt", hr.getPris()));
                } else {
                    sb.append("    Intet hotel reserveret.\n");
                }
                sb.append(String.format("    Totalpris for denne tilmelding: %.2f kr\n", calculateTotalPrice(tilmelding)));
                sb.append("    ----------------------------------------\n");
            }
        }
        sb.append("---------------------------------------------------------------------------------\n");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // Udflugt

    public static Udflugt createUdflugtForKonference(Konference konference, String navn, double pris, LocalDate tidspunkt) {
        Udflugt udflugt = konference.createUdflugt(navn, pris, tidspunkt);
        Storage.addUdflugt(udflugt);
        return udflugt;
    }

    public static void removeUdflugtFromKonference(Konference konference, Udflugt udflugt) {
        konference.removeUdflugt(udflugt);
        Storage.removeUdflugt(udflugt);
    }

        public static ArrayList<Udflugt> getUdflugter() {

            return Storage.getUdflugter();

        }

    

        public static void initStorage() {

                        // Konference

                        Administrator admin = createAdministrator("Admin Navn");

                        Konference havOgHimmel = createKonference("Hav og Himmel", "Odense",

                                LocalDate.of(2025, 11, 28), LocalDate.of(2025, 11, 30), 1500, admin);

    

            // Hoteller

            Hotel denHvideSvane = createHotel("Den Hvide Svane", "Overgade 25, 5000 Odense C", "Lækkert hotel i centrum");

            denHvideSvane.createService("WIFI", 50);

    

            Hotel hoetelPhoenix = createHotel("Hotel Phønix", "Vestergade 75, 5000 Odense C", "Gammelt, men pænt hotel");

            hoetelPhoenix.createService("Bad", 200);

            hoetelPhoenix.createService("WIFI", 75);

    

            Hotel pensionTusindfryd = createHotel("Pension Tusindfryd", "Store Glasvej 4, 5000 Odense C", "Hyggeligt B&B");

            pensionTusindfryd.createService("Morgenmad", 100);

    

            addHotelToKonference(havOgHimmel, denHvideSvane);

            addHotelToKonference(havOgHimmel, hoetelPhoenix);

            addHotelToKonference(havOgHimmel, pensionTusindfryd);

    

            // Udflugter

                        createUdflugtForKonference(havOgHimmel, "Byrundtur i Odense inkl. Frokost", 125.0, LocalDate.of(2025, 11, 28));

                        createUdflugtForKonference(havOgHimmel, "Egeskov", 75.0, LocalDate.of(2025, 11, 29));

                        createUdflugtForKonference(havOgHimmel, "Trapholt Museum, Kolding inkl. frokost", 200.0, LocalDate.of(2025, 11, 30));

        }

    }

    