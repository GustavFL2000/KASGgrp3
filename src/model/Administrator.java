package model;

import storage.Storage;

import java.util.ArrayList;

public class Administrator {
    private String navn;
    private ArrayList<Konference> konferencer = new ArrayList<>();

    public Administrator(String navn) {
        this.navn = navn;
    }

    public static Administrator create(String navn) {
        Administrator admin = new Administrator(navn);
        storage.Storage.addAdministrator(admin);
        return admin;
    }

    public ArrayList<Konference> getKonferencer() {
        return new ArrayList<>(konferencer);
    }

    public void addKonference(Konference konference){
        if (!konferencer.contains(konference)){
            konferencer.add(konference);
            konference.setAdministrator(this);
        }
    }

    public void removeKonference(Konference konference){
        if (konferencer.contains(konference)){
            konferencer.remove(konference);
            konference.setAdministrator(null);
        }
    }

    // Internal helpers used by Konference to avoid recursive calls
    void _addKonference(Konference konference) {
        if (!konferencer.contains(konference)) {
            konferencer.add(konference);
        }
    }

    void _removeKonference(Konference konference) {
        konferencer.remove(konference);
    }
}
