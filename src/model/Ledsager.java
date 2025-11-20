package model;

import java.util.ArrayList;

public class Ledsager {
    private String navn;

    private ArrayList<Udflugt> udflugter = new ArrayList<>();

    public Ledsager(String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    public void addUdflugt(Udflugt udflugt) {
        if (!udflugter.contains(udflugt)) {
            udflugter.add(udflugt);
            udflugt._addLedsager(this); // ingen gensidig kald!
        }
    }

    public ArrayList<Udflugt> getUdflugter() {
        return new ArrayList<>(udflugter);
    }

    void _addUdflugt(Udflugt udflugt) {
        if (!udflugter.contains(udflugt)) {
            udflugter.add(udflugt);
        }
    }


}
