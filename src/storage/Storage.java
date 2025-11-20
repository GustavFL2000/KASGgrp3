package storage;

import model.*;

import java.util.ArrayList;

public class Storage {
    private static final ArrayList<Konference> konferencer = new ArrayList<>();
    private static final ArrayList<Deltager> deltagere = new ArrayList<>();
    private static final ArrayList<Hotel> hoteller = new ArrayList<>();
    private static final ArrayList<Udflugt> udflugter = new ArrayList<>();
    private static final ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private static final ArrayList<Firma> firmaer = new ArrayList<>();
    private static final ArrayList<Administrator> administratorer = new ArrayList<>();


    // Konference
    public static ArrayList<Konference> getKonferencer() {
        return new ArrayList<>(konferencer);
    }

    public static void addKonference(Konference konference) {
        konferencer.add(konference);
    }

    public static void removeKonference(Konference konference) {
        konferencer.remove(konference);
    }

    // Deltager
    public static ArrayList<Deltager> getDeltagere() {
        return new ArrayList<>(deltagere);
    }

    public static void addDeltager(Deltager deltager) {
        deltagere.add(deltager);
    }

    public static void removeDeltager(Deltager deltager) {
        deltagere.remove(deltager);
    }

    // Hotel
    public static ArrayList<Hotel> getHoteller() {
        return new ArrayList<>(hoteller);
    }

    public static void addHotel(Hotel hotel) {
        hoteller.add(hotel);
    }

    public static void removeHotel(Hotel hotel) {
        hoteller.remove(hotel);
    }

    // Udflugt
    public static ArrayList<Udflugt> getUdflugter() {
        return new ArrayList<>(udflugter);
    }

    public static void addUdflugt(Udflugt udflugt) {
        udflugter.add(udflugt);
    }

    public static void removeUdflugt(Udflugt udflugt) {
        udflugter.remove(udflugt);
    }

    // Tilmelding
    public static ArrayList<Tilmelding> getTilmeldinger() {
        return new ArrayList<>(tilmeldinger);
    }

    public static void addTilmelding(Tilmelding tilmelding) {
        tilmeldinger.add(tilmelding);
    }

    public static void removeTilmelding(Tilmelding tilmelding) {
        tilmeldinger.remove(tilmelding);
    }

    // Firma
    public static ArrayList<Firma> getFirmaer() {
        return new ArrayList<>(firmaer);
    }

    public static void addFirma(Firma firma) {
        firmaer.add(firma);
    }

    public static void removeFirma(Firma firma) {
        firmaer.remove(firma);
    }

    // Administrator
    public static ArrayList<Administrator> getAdministratorer() {
        return new ArrayList<>(administratorer);
    }

    public static void addAdministrator(Administrator admin) {
        administratorer.add(admin);
    }

    public static void removeAdministrator(Administrator admin) {
        administratorer.remove(admin);
    }
}