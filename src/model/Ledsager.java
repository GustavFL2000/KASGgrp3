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

    public ArrayList<Udflugt> getUdflugter() {
        return new ArrayList<>(udflugter);
    }

    // Offentlig metode (fra GUI / deltager)
    public void addUdflugt(Udflugt udflugt) {
        if (!udflugter.contains(udflugt)) {
            udflugter.add(udflugt);
            udflugt.addLedsager(this);   // kalder udflugtens metode
        }
    }

    // Intern metode — bruges KUN fra Udflugt.addLedsager()
    void _addUdflugtFromUdflugt(Udflugt udflugt) {
        if (!udflugter.contains(udflugt)) {
            udflugter.add(udflugt);
        }
    }

    @Override
    public String toString() {
        return navn;
    }
}
