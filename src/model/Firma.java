package model;

public class Firma {

    private String navn;
    private String telefon;

    public Firma(String navn, String telefon) {
        this.navn = navn;
        this.telefon = telefon;
    }

    public String getNavn() {
        return navn;
    }

    public String getTelefon() {
        return telefon;
    }
}
