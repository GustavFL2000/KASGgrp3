package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Udflugt {
    private String navn;
    private int pris;
    private LocalDate tidspunkt;
    private ArrayList<Ledsager> ledsagere = new ArrayList<>();
    private Konference konference;

    public Udflugt(String navn, int pris, LocalDate tidspunkt, Konference konference) {
        this.navn = navn;
        this.pris = pris;
        this.tidspunkt = tidspunkt;
        this.konference = konference;
    }


    public int getPris() {
        return pris;
    }

    public Konference getKonference() {
        return konference;
    }

    public void addLedsager(Ledsager l) {
        if (!ledsagere.contains(l)) {
            ledsagere.add(l);
            l._addUdflugt(this); // ensrettet kald
        }
    }

    void _addLedsager(Ledsager l) {
        if (!ledsagere.contains(l)) {
            ledsagere.add(l);
        }
    }


}
