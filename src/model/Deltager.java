package model;

import java.util.ArrayList;

public class Deltager {
    private String navn;
    private String adresse;
    private String nationalitet;
    private String email;
    private String telefon;
    private boolean erForedragsholder;
    private Ledsager ledsager;
    private Firma firma;
    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();

    public Deltager(String navn, String adresse, String nationalitet, String email, String telefon, boolean erForedragsholder) {
        this.navn = navn;
        this.adresse = adresse;
        this.nationalitet = nationalitet;
        this.email = email;
        this.telefon = telefon;
        this.erForedragsholder = erForedragsholder;
    }

    // Gettere / Settere
    public String getNavn() { return navn; }
    public boolean isErForedragsholder() { return erForedragsholder; }
    public Ledsager getLedsager() { return ledsager; }
    public Firma getFirma() { return firma; }
    public ArrayList<Tilmelding> getTilmeldinger() { return new ArrayList<>(tilmeldinger); }

    public void setFirma(Firma firma) {
        if (this.firma == firma) return;
        this.firma = firma;
        if (firma != null) firma.addDeltager(this);
    }

    public void setLedsager(Ledsager ledsager) {
        this.ledsager = ledsager;
    }

    //  create ledsager
    public Ledsager createLedsager(String navn) {
        Ledsager l = new Ledsager(navn);
        this.setLedsager(l);
        return l;
    }

    // Relation
    public void addTilmelding(Tilmelding t) {
        if (!tilmeldinger.contains(t)) {
            tilmeldinger.add(t);
        }
    }

    @Override
    public String toString() {
        return navn;
    }

}
