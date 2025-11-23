package controller;

import model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

    // -------------------------------------------------------------------------
    // Konference


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


    public static void setAdministratorOfKonference(Konference konference, Administrator admin) {
        konference.setAdministrator(admin);
    }


    // -------------------------------------------------------------------------
    // Deltager


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


    public static void addUdflugtToLedsager(Ledsager ledsager, Udflugt udflugt) {
        ledsager.addUdflugt(udflugt);
    }


    // -------------------------------------------------------------------------
    // Firma


    public static ArrayList<Firma> getFirmaer() {
        return Storage.getFirmaer();
    }

    // -------------------------------------------------------------------------
    // Hotel


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


    public static ArrayList<Tilmelding> getTilmeldinger() {
        return Storage.getTilmeldinger();
    }

    // -------------------------------------------------------------------------
    // Prisberegning

    public static double calculateTotalPrice(Tilmelding tilmelding) {
        return tilmelding.getTotalPris();
    }
}

// -------------------------------------------------------------------------

