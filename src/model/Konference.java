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

<<<<<<< HEAD
    public Konference(String navn, String sted, LocalDate startDato, LocalDate slutDato, int dagsPris) {
=======
    public Konference(String navn, String sted, LocalDate startDato, LocalDate slutDato, int dagsPris, Adminstrator adminstrator) {
>>>>>>> 932188bf8a0f3e5d82720bd217d0e15c12d78141
        this.navn = navn;
        this.sted = sted;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.dagsPris = dagsPris;
    }

    public int getDagsPris() {
        return dagsPris;
    }

    public ArrayList<Udflugt> getUdflugter() {
        return new ArrayList<>(udflugter);
    }

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
        if (this.administrator != administrator) {
            Administrator oldAdmin = this.administrator;
            if (oldAdmin != null) {
                oldAdmin.removeKonference(this);
            }

            this.administrator = administrator;
            if (administrator != null) {
                administrator.addKonference(this);
            }
        }
    }

    public Udflugt createUdflugt(String navn, int pris, LocalDate tidspunkt) {
        Udflugt u = new Udflugt(navn, pris, tidspunkt, this);
        udflugter.add(u);
        return u;
    }



    public void removeUdflugt(Udflugt udflugt) {
        if (udflugter.contains(udflugt)) {
            udflugter.remove(udflugt);
        }
    }

    public void setAdminstrator(Administrator administrator) {
        this.administrator = administrator;
    }
}
