package model;

import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Tilmelding {
    private int AntalDage;
    private LocalDate ankomstDato;
    private LocalDate afrejseDato;
    private int totalPris;
    private Konference konference;
    private Deltager deltager;
    private ArrayList<HotelReservation> hotelReservationer = new ArrayList<>();

    public Tilmelding(int antalDage, LocalDate ankomstDato, LocalDate afrejseDato, int totalPris, Konference konference, Deltager deltager) {
        AntalDage = antalDage;
        this.ankomstDato = ankomstDato;
        this.afrejseDato = afrejseDato;
        this.totalPris = totalPris;
        this.konference = konference;
        this.deltager = deltager;
    }

    public ArrayList<HotelReservation> getHotelReservation() {
        return hotelReservationer;
    }

    public HotelReservation createHotelreservation(boolean isDoubleRoom, double pris, Hotel hotel, Tilmelding tilmelding) {
        HotelReservation hotelReservation = new HotelReservation(isDoubleRoom, pris, hotel, tilmelding);
        hotelReservationer.add(hotelReservation);
        return hotelReservation;
    }

    public void removeHotelReservation(HotelReservation hotelReservation) {
        if (hotelReservationer.contains(hotelReservation)){
            hotelReservationer.remove(hotelReservation);
        }
    }
}
