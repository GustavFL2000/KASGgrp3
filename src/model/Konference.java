package model;

import javax.management.Attribute;
import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {
    private String navn;
    private String sted;
    private LocalDate startDato;
    private LocalDate slutDato;
    private int dagsPris;
    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private ArrayList<Udflugt> udflugter = new ArrayList<>();
    private ArrayList<Hotel> hoteller = new ArrayList<>();
    private Adminstrator adminstrator;

    public Konference(String navn, String sted, LocalDate startDato, LocalDate slutDato, int dagsPris, ArrayList<Tilmelding> tilmeldinger, ArrayList<Udflugt> udflugter, ArrayList<Hotel> hoteller, Adminstrator adminstrator) {
        this.navn = navn;
        this.sted = sted;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.dagsPris = dagsPris;
        this.tilmeldinger = tilmeldinger;
        this.udflugter = udflugter;
        this.hoteller = hoteller;
        this.adminstrator = adminstrator;
    }

    public ArrayList<Udflugt> getUdflugter() {
        return udflugter;
    }

    public Udflugt createUdflugt(String udflugtName){
        Udflugt udflugt = new Udflugt("Udflugt test",200,LocalDate.now(), new ArrayList<>(),this);
        udflugter.add(udflugt);
        return udflugt;
    }
    public void removeUdflugt(Udflugt udflugt){
        if (udflugter.contains(udflugt)){
            udflugter.remove(udflugt);
        }
    }
}
