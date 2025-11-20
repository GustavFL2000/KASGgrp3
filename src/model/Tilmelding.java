package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tilmelding {
    private int antalDage;
    private LocalDate ankomstDato;
    private LocalDate afrejseDato;
    private Konference konference;
    private Deltager deltager;
    private HotelReservation hotelReservation; // Changed to single object

    public Tilmelding(int antalDage, LocalDate ankomstDato, LocalDate afrejseDato, Konference konference, Deltager deltager) {
        this.antalDage = antalDage;
        this.ankomstDato = ankomstDato;
        this.afrejseDato = afrejseDato;
        this.konference = konference;
        this.deltager = deltager;
    }

    public double getTotalPris() {
        double totalPris = 0;

        // Conference fee
        if (!deltager.isErForedragsholder()) {
            totalPris += konference.getDagsPris() * antalDage;
        }

        // Hotel reservation price and services
        if (hotelReservation != null) {
            totalPris += hotelReservation.getPris();
            for (Service service : hotelReservation.getServices()) { // Assuming HotelReservation has getServices()
                totalPris += service.getPris();
            }
        }

        // Excursion prices
        if (deltager.getLedsager() != null) {
            for (Udflugt udflugt : deltager.getLedsager().getUdflugter()) {
                totalPris += udflugt.getPris();
            }
        }
        return totalPris;
    }

    public Konference getKonference() {
        return konference;
    }

    public Deltager getDeltager() {
        return deltager;
    }

    public int getAntalDage() {
        return antalDage;
    }

    public LocalDate getAnkomstDato() {
        return ankomstDato;
    }

    public LocalDate getAfrejseDato() {
        return afrejseDato;
    }

    public HotelReservation getHotelReservation() {
        return hotelReservation;
    }

    public HotelReservation createHotelreservation(boolean isDoubleRoom, double pris, Hotel hotel, Tilmelding tilmelding) {
        if (this.hotelReservation != null) {
            // Potentially throw an exception or handle this case as per business rules
            System.out.println("Warning: Overwriting existing hotel reservation for this Tilmelding.");
        }
        HotelReservation newReservation = new HotelReservation(isDoubleRoom, pris, hotel, tilmelding);
        this.hotelReservation = newReservation;
        return newReservation;
    }

    public void removeHotelReservation(HotelReservation hotelReservation) {
        if (this.hotelReservation != null && this.hotelReservation.equals(hotelReservation)){
            this.hotelReservation = null;
        }
    }
}
