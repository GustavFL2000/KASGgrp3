package model;

import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {
    private String navn;
    private String sted;
    private LocalDate startDato;
    private LocalDate slutDato;
    private int dagsPris;
    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private ArrayList<Udflugt> udflugter = new ArrayList<>();
    private ArrayList<Hotel> hoteller = new ArrayList<>();
    private Administrator administrator;

    public Konference(String navn, String sted, LocalDate startDato, LocalDate slutDato, int dagsPris, Administrator adminstrator) {
        this.navn = navn;
        this.sted = sted;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.dagsPris = dagsPris;
        this.setAdministrator(adminstrator);
    }

    public static Konference create(String navn, String sted, LocalDate startDato, LocalDate slutDato, int dagsPris, Administrator administrator) {
        Konference konference = new Konference(navn, sted, startDato, slutDato, dagsPris, administrator);
        storage.Storage.addKonference(konference);
        return konference;
    }

    public String getNavn() { return navn; }
    public String getSted() { return sted; }
    public LocalDate getStartDato() { return startDato; }
    public LocalDate getSlutDato() { return slutDato; }
    public ArrayList<Hotel> getHoteller() { return new ArrayList<>(hoteller); }
    public ArrayList<Tilmelding> getTilmeldinger() { return new ArrayList<>(tilmeldinger); }
    public int getDagsPris() { return dagsPris; }
    public ArrayList<Udflugt> getUdflugter() { return new ArrayList<>(udflugter); }

    public void addTilmelding(Tilmelding tilmelding) {
        if (!tilmeldinger.contains(tilmelding)) {
            tilmeldinger.add(tilmelding);
        }
    }

    public void removeTilmelding(Tilmelding tilmelding) {
        tilmeldinger.remove(tilmelding);
    }

    public void addHotel(Hotel hotel) {
        if (!hoteller.contains(hotel)) {
            hoteller.add(hotel);
        }
    }

    public void removeHotel(Hotel hotel) {
        hoteller.remove(hotel);
    }

    public void setAdministrator(Administrator administrator) {
        if (this.administrator == administrator) return;

        Administrator oldAdmin = this.administrator;
        this.administrator = administrator;

        if (oldAdmin != null) {
            oldAdmin._removeKonference(this); // use internal helper to avoid recursion
        }
        if (administrator != null) {
            administrator._addKonference(this); // use internal helper to avoid recursion
        }
    }

    public Udflugt createUdflugt(String navn, double pris, LocalDate tidspunkt) {
        Udflugt u = new Udflugt(navn, pris, tidspunkt, this);
        udflugter.add(u);
        storage.Storage.addUdflugt(u); // Add to central storage
        return u;
    }

    public void removeUdflugt(Udflugt udflugt) {
        if (udflugter.contains(udflugt)) {
            udflugter.remove(udflugt);
        }
    }
    public Tilmelding createTilmelding(Deltager deltager, LocalDate ankomstDato, LocalDate afrejseDato, int antalDage) {
        Tilmelding tilmelding = new Tilmelding(antalDage, ankomstDato, afrejseDato, this, deltager);
        this.addTilmelding(tilmelding);
        deltager.addTilmelding(tilmelding);
        storage.Storage.addTilmelding(tilmelding); // Add to central storage
        return tilmelding;
    }
}
