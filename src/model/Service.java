package model;

public class Service {
    private String navn;
    private double pris;

    public Service(String navn, double pris) {
        this.navn = navn;
        this.pris = pris;
    }

    public String getNavn() {
        return navn;
    }

    public double getPris() {
        return pris;
    }

    @Override
    public String toString() {
        return "Service{" +
                "navn='" + navn + '\'' +
                ", pris=" + pris +
                '}';
    }
}
