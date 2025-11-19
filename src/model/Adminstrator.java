package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Adminstrator {
    private String navn;
    private ArrayList<Konference> konferencer = new ArrayList<>();

    public Adminstrator(ArrayList<Konference> konferencer, String navn) {
        this.konferencer = konferencer;
        this.navn = navn;
    }

    public ArrayList<Konference> getKonferencer() {
        return new ArrayList<>(konferencer);
    }


    public void addKonference(Konference konference){
        if (!konferencer.contains(konference)){
            konferencer.add(konference);
            konference.setAdminstrator(this);
        }
    }

    public void removeKonference(Konference konference){
        if (konferencer.contains(konference)){
            konferencer.remove(konference);
            konference.setAdminstrator(null);
        }

        }
    }

