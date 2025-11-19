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
    private final ArrayList<Tilmelding> tilmeldinger= new ArrayList<>();
    private Firma firma;

    public Deltager(String navn, String adresse, String nationalitet, String email, String telefon, boolean erForedragsholder) {
        this.navn = navn;
        this.adresse = adresse;
        this.nationalitet = nationalitet;
        this.email = email;
        this.telefon = telefon;
        this.erForedragsholder = erForedragsholder;
    }

    public void setFirma(Firma firma) {
        if (this.firma != firma) {
            Firma oldFirma = this.firma;
            if (oldFirma != null) {
                oldFirma.removeDeltager(this);
            }
            this.firma = firma;
            if (firma != null) {
                firma.addDeltager(this);
            }
        }
    }

    public String getNavn() {
        return navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNationalitet() {
        return nationalitet;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    public boolean isErForedragsholder() {
        return erForedragsholder;
    }

    public ArrayList<Tilmelding> getTilmeldinger() {
        return new ArrayList<>(tilmeldinger);
    }

    public void addTilmelding(Tilmelding tilmelding) {
        if (!tilmeldinger.contains(tilmelding)) {
            tilmeldinger.add(tilmelding);
        }
    }

    public void removeTilmelding(Tilmelding tilmelding) {
        if (tilmeldinger.contains(tilmelding)) {
            tilmeldinger.remove(tilmelding);

        }
    }
}
