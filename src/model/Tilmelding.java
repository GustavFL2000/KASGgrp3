package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tilmelding {
    private int AntalDage;
    private LocalDate ankomstDato;
    private LocalDate afrejseDato;
    private double totalPris;
    private Konference konference;
    private Deltager deltager;
    private ArrayList<HotelReservation> hotelReservationer = new ArrayList<>();

    public Tilmelding(int antalDage, LocalDate ankomstDato, LocalDate afrejseDato, Konference konference, Deltager deltager) {
        AntalDage = antalDage;
        this.ankomstDato = ankomstDato;
        this.afrejseDato = afrejseDato;
        this.konference = konference;
        this.deltager = deltager;
    }

    public double beregnTotalPris() {
        double totalPris = 0;

        // Conference fee
        if (!deltager.isErForedragsholder()) {
            totalPris += konference.getDagsPris() * AntalDage;
        }

        // Hotel reservation price and services
        for (HotelReservation reservation : hotelReservationer) {
            totalPris += reservation.getPris();
            for (Service service : reservation.getServices()) {
                totalPris += service.getPris();
            }
        }

        // Excursion prices
        if (deltager.getLedsager() != null) {
            for (Udflugt udflugt : deltager.getLedsager().getUdflugter()) {
                totalPris += udflugt.getPris();
            }
        }

        this.totalPris = totalPris;
        return totalPris;
    }

    public Konference getKonference() {
        return konference;
    }

    public Deltager getDeltager() {
        return deltager;
    }

    public int getAntalDage() {
        return AntalDage;
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
