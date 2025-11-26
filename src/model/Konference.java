package model;

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

    public Konference(String navn, String sted, LocalDate startDato, LocalDate slutDato, int dagsPris, Administrator administrator) {
        this.navn = navn;
        this.sted = sted;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.dagsPris = dagsPris;
        this.setAdministrator(administrator);
    }

    // Gettere
    public String getNavn() { return navn; }
    public LocalDate getStartDato() { return startDato; }
    public LocalDate getSlutDato() { return slutDato; }
    public int getDagsPris() { return dagsPris; }

    public ArrayList<Tilmelding> getTilmeldinger() { return new ArrayList<>(tilmeldinger); }
    public ArrayList<Udflugt> getUdflugter() { return new ArrayList<>(udflugter); }
    public ArrayList<Hotel> getHoteller() { return new ArrayList<>(hoteller); }

    // Relationer
    public void addTilmelding(Tilmelding tilmelding) {
        if (!tilmeldinger.contains(tilmelding)) {
            tilmeldinger.add(tilmelding);
        }
    }

    public void addHotel(Hotel hotel) {
        if (!hoteller.contains(hotel)) {
            hoteller.add(hotel);
        }
    }

    // Administrator relation
    public void setAdministrator(Administrator administrator) {
        if (this.administrator == administrator) return;

        Administrator old = this.administrator;
        this.administrator = administrator;

        if (old != null) {
            old._removeKonference(this);
        }
        if (administrator != null) {
            administrator._addKonference(this);
        }
    }

    // Domæne-oprettelser
    public Udflugt createUdflugt(String navn, double pris, LocalDate tidspunkt) {
        Udflugt udflugt = new Udflugt(navn, pris, tidspunkt, this);
        udflugter.add(udflugt);
        return udflugt;
    }

    public Tilmelding createTilmelding(Deltager deltager, LocalDate ankomstDato, LocalDate afrejseDato, int antalDage) {
        Tilmelding tilmelding = new Tilmelding(antalDage, ankomstDato, afrejseDato, this, deltager);

        // KORREKT STED at registrere tilmelding hos konferencen
        this.addTilmelding(tilmelding);

        // Registrer tilmelding hos deltageren
        if (deltager != null) {
            deltager.addTilmelding(tilmelding);
        }

        return tilmelding;
    }
    @Override
    public String toString() {
        return navn;
    }

}
