package model;

import java.util.ArrayList;

public class Administrator {
    private String navn;
    private ArrayList<Konference> konferencer = new ArrayList<>();

    public Administrator(String navn) {
        this.navn = navn;
    }

    public String getNavn() { return navn; }
    public ArrayList<Konference> getKonferencer() { return new ArrayList<>(konferencer); }

    public void addKonference(Konference k) {
        if (!konferencer.contains(k)) {
            konferencer.add(k);
            k.setAdministrator(this);
        }
    }

    // internal helper for Konference.setAdministrator to avoid recursion
    void _addKonference(Konference k) {
        if (!konferencer.contains(k)) {
            konferencer.add(k);
        }
    }

    void _removeKonference(Konference k) {
        konferencer.remove(k);
    }
}
