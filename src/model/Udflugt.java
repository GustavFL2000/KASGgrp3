package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Udflugt {
    private String navn;
    private double pris; // Changed to double
    private LocalDate tidspunkt;
    private ArrayList<Ledsager> ledsagere = new ArrayList<>();
    private Konference konference;

    public Udflugt(String navn, double pris, LocalDate tidspunkt, Konference konference) { // Updated constructor
        this.navn = navn;
        this.pris = pris;
        this.tidspunkt = tidspunkt;
        this.konference = konference;
    }


    public double getPris() { // Updated return type
        return pris;
    }

    public String getNavn() { // Added missing getter
        return navn;
    }

    public LocalDate getTidspunkt() { // Added missing getter
        return tidspunkt;
    }

    public Konference getKonference() {
        return konference;
    }

    public void addLedsager(Ledsager l) {
        if (!ledsagere.contains(l)) {
            ledsagere.add(l);
            l._addUdflugt(this);
        }
    }

    void _addLedsager(Ledsager l) {
        if (!ledsagere.contains(l)) {
            ledsagere.add(l);
        }
    }


}
