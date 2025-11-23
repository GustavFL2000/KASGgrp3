package model;

import storage.Storage;

import java.util.ArrayList;

public class Firma {
    private String navn;
    private String telefon;
    private final ArrayList<Deltager> deltagere = new ArrayList<>();

    public Firma(String navn, String telefon) {
        this.navn = navn;
        this.telefon = telefon;
    }

    public static Firma create(String navn, String telefon) {
        Firma firma = new Firma(navn, telefon);
        storage.Storage.addFirma(firma);
        return firma;
    }

    public ArrayList<Deltager> getDeltagere() {
        return new ArrayList<>(deltagere);
    }

    public void addDeltager(Deltager deltager) {
        if (!deltagere.contains(deltager)) {
            deltagere.add(deltager);
            deltager.setFirma(this);
        }
    }

    public void removeDeltager(Deltager deltager) {
        if (deltagere.contains(deltager)) {
            deltagere.remove(deltager);
            deltager.setFirma(null);
        }
    }

    // Internal helpers used by Deltager to avoid recursion
    void _addDeltager(Deltager deltager) {
        if (!deltagere.contains(deltager)) {
            deltagere.add(deltager);
        }
    }

    void _removeDeltager(Deltager deltager) {
        deltagere.remove(deltager);
    }

    public String getNavn() { return navn; }
    public String getTelefon() { return telefon; }
}
