package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Udflugt {

    private String navn;
    private double pris;
    private LocalDate tidspunkt;
    private Konference konference;
    private ArrayList<Ledsager> ledsagere = new ArrayList<>();

    public Udflugt(String navn, double pris, LocalDate tidspunkt, Konference konference) {
        this.navn = navn;
        this.pris = pris;
        this.tidspunkt = tidspunkt;
        this.konference = konference;
    }

    // Gettere
    public String getNavn() {
        return navn;
    }

    public double getPris() {
        return pris;
    }


    public Konference getKonference() {
        return konference;
    }

    // Relationer
    public void addLedsager(Ledsager l) {
        if (!ledsagere.contains(l)) {
            ledsagere.add(l);
            // Sørg for at udflugten også sættes på ledsageren
            l._addUdflugtFromUdflugt(this);
        }
    }

    @Override
    public String toString() {
        return navn + " (" + pris + " kr.)";
    }
}
