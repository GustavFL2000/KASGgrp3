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

        // Hotel reservation price (now calculated by HotelReservation)
        if (hotelReservation != null) {
            totalPris += hotelReservation.beregnPris();
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

    public HotelReservation createHotelreservation(boolean isDoubleRoom, Hotel hotel) {
        if (this.hotelReservation != null) {
            // Potentially throw an exception or handle this case as per business rules
            System.out.println("Warning: Overwriting existing hotel reservation for this Tilmelding.");
        }
        // Price is no longer passed here; it's calculated by the reservation itself.
        HotelReservation newReservation = new HotelReservation(isDoubleRoom, hotel, this);
        this.hotelReservation = newReservation;
        return newReservation;
    }

    public void removeHotelReservation(HotelReservation hotelReservation) {
        if (this.hotelReservation != null && this.hotelReservation.equals(hotelReservation)){
            this.hotelReservation = null;
        }
    }
}
