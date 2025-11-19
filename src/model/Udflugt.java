package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Udflugt {
    private String navn;
    private int pris;
    private LocalDate tidspunkt;
    private ArrayList<Ledsager> ledsagere = new ArrayList<>();
    private Konference konference;

    public Udflugt(String navn, int pris, LocalDate tidspunkt, ArrayList<Ledsager> ledsagere, Konference konference) {
        this.navn = navn;
        this.pris = pris;
        this.tidspunkt = tidspunkt;
        this.ledsagere = ledsagere;
        this.konference = konference;
    }
public Konference getKonference(){
        return konference;
}


}
