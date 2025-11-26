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

    // Deltager
    public static ArrayList<Deltager> getDeltagere() {
        return new ArrayList<>(deltagere);
    }

    public static void addDeltager(Deltager deltager) {
        deltagere.add(deltager);
    }

    // Hotel

    public static void addHotel(Hotel hotel) {
        hoteller.add(hotel);
    }

    // Udflugt

    public static void addUdflugt(Udflugt udflugt) {
        udflugter.add(udflugt);
    }

    // Tilmelding

    public static void addTilmelding(Tilmelding tilmelding) {
        tilmeldinger.add(tilmelding);
    }

    public static void addFirma(Firma firma) {
        firmaer.add(firma);
    }

    public static void addAdministrator(Administrator admin) {
        administratorer.add(admin);
    }

}