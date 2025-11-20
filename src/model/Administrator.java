package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Administrator {
    private String navn;
    private ArrayList<Konference> konferencer = new ArrayList<>();

    public Administrator(String navn) {
        this.navn = navn;
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
    }

