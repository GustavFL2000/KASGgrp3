package controller;

import model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

    // -------------------------------------------------------------------------
    // Konference

    public static Konference createKonference(String navn, String sted, LocalDate startDato, LocalDate slutDato, int dagsPris) {
        Konference konference = new Konference(navn, sted, startDato, slutDato, dagsPris);
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
    // Udflugt

    public static Udflugt createUdflugtForKonference(Konference konference, String navn, int pris, LocalDate tidspunkt) {
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

            Konference havOgHimmel = createKonference("Hav og Himmel", "Odense",

                    LocalDate.of(2025, 11, 28), LocalDate.of(2025, 11, 30), 1500);

    

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

            createUdflugtForKonference(havOgHimmel, "Byrundtur i Odense inkl. Frokost", 125, LocalDate.of(2025, 11, 28));

            createUdflugtForKonference(havOgHimmel, "Egeskov", 75, LocalDate.of(2025, 11, 29));

            createUdflugtForKonference(havOgHimmel, "Trapholt Museum, Kolding inkl. frokost", 200, LocalDate.of(2025, 11, 30));

        }

    }

    