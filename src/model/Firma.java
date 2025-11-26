package model;

import java.util.ArrayList;

public class Firma {
    private String navn;
    private String telefon;
    private ArrayList<Deltager> deltagere = new ArrayList<>();

    public Firma(String navn, String telefon) {
        this.navn = navn;
        this.telefon = telefon;
    }

    public String getNavn() { return navn; }

    public void addDeltager(Deltager d) {
        if (!deltagere.contains(d)) {
            deltagere.add(d);
            d.setFirma(this);
        }
    }
}
