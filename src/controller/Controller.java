package controller;

import model.*;
import storage.Storage;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {


    //Konference
    public static Konference createKonference(String navn, String sted, LocalDate startDato, LocalDate slutDato, int dagsPris, Administrator administrator) {
        Konference konference = new Konference(navn, sted, startDato, slutDato, dagsPris, administrator);
        Storage.addKonference(konference);
        return konference;
    }

    public static ArrayList<Konference> getKonferencer() {
        return Storage.getKonferencer();
    }

    public static Udflugt createUdflugtForKonference(Konference konference, String navn, double pris, LocalDate tidspunkt) {
        Udflugt udflugt = konference.createUdflugt(navn, pris, tidspunkt);
        Storage.addUdflugt(udflugt);
        return udflugt;
    }


    public static Tilmelding createTilmeldingForKonference(int antalDage, LocalDate ankomstDato, LocalDate afrejseDato, Konference konference, Deltager deltager) {

        Tilmelding tilmelding = konference.createTilmelding(deltager, ankomstDato, afrejseDato, antalDage);
        Storage.addTilmelding(tilmelding);
        return tilmelding;
    }


    //Delatger + firma

    // store version (med firma)
    public static Deltager createDeltager(String navn, String adresse, String nationalitet, String email, String telefon, boolean erForedragsholder, Firma firma) {

        Deltager deltager = new Deltager(navn, adresse, nationalitet, email, telefon, erForedragsholder);
        if (firma != null) {
            deltager.setFirma(firma);
        }
        Storage.addDeltager(deltager);
        return deltager;
    }

    // lille version (uden firma) → GUI / controller.Demo bruger denne
    public static Deltager createDeltager(String navn, String adresse, String nationalitet, String telefon, String email, boolean erForedragsholder) {
        return createDeltager(navn, adresse, nationalitet, email, telefon, erForedragsholder, null);
    }

    public static ArrayList<Deltager> getDeltagere() {
        return Storage.getDeltagere();
    }

    public static Firma createFirma(String navn, String telefon) {
        Firma firma = new Firma(navn, telefon);
        Storage.addFirma(firma);
        return firma;
    }


//    Administrator
    public static Administrator createAdministrator(String navn) {
        Administrator administrator = new Administrator(navn);
        Storage.addAdministrator(administrator);
        return administrator;
    }


    // Hotel + HotelReservation + Service

    public static Hotel createHotel(String navn, String adresse, String beskrivelse, double prisEnkelt, double prisDobbelt) {
        Hotel hotel = new Hotel(navn, adresse, beskrivelse, prisEnkelt, prisDobbelt);
        Storage.addHotel(hotel);
        return hotel;
    }


    //Find deltager, konference, hotel, udflugt by navn
    public static Konference getKonferenceByNavn(String navn) {
        for (Konference konference : Storage.getKonferencer()) {
            if (konference.getNavn().equals(navn)) {
                return konference;
            }
        }
        return null;
    }

    public static Hotel getHotelByNavn(Konference konference, String navn) {
        for (Hotel hotel : konference.getHoteller()) {
            if (hotel.getNavn().equals(navn)) {
                return hotel;
            }
        }
        return null;
    }

    public static Udflugt getUdflugtByNavn(Konference konference, String navn) {
        for (Udflugt udflugt : konference.getUdflugter()) {
            if (udflugt.getNavn().equals(navn)) {
                return udflugt;
            }
        }
        return null;
    }
}
