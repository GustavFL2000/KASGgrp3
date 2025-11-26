package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tilmelding {
    private LocalDate ankomstDato;
    private LocalDate afrejseDato;
    private int antalDage;
    private Konference konference;
    private Deltager deltager;
    private HotelReservation hotelReservation;
    private ArrayList<Service> services = new ArrayList<>();

    public Tilmelding(int antalDage, LocalDate ankomstDato, LocalDate afrejseDato,
                      Konference konference, Deltager deltager) {
        this.antalDage = antalDage;
        this.ankomstDato = ankomstDato;
        this.afrejseDato = afrejseDato;
        this.konference = konference;
        this.deltager = deltager;
    }

    // Gettere
    public LocalDate getAnkomstDato() { return ankomstDato; }
    public LocalDate getAfrejseDato() { return afrejseDato; }
    public Konference getKonference() { return konference; }
    public Deltager getDeltager() { return deltager; }
    public HotelReservation getHotelReservation() { return hotelReservation; }


    public HotelReservation createHotelReservation(boolean isDoubleRoom, Hotel hotel) {
        HotelReservation hotelReservation1 = new HotelReservation(isDoubleRoom, hotel, this);
        this.hotelReservation = hotelReservation1;
        return hotelReservation1;
    }


    // Beregning
    public double getTotalPris() {

        double prisKonference = deltager.isErForedragsholder() // er foredragsholder?
                ? 0
                : konference.getDagsPris() * antalDage;

        double prisHotel = hotelReservation != null ? hotelReservation.beregnPris() : 0; // tjek for null

        double prisServices = services.stream().mapToDouble(Service::getPris).sum(); // services pris

        double prisUdflugter = 0;
        if (deltager.getLedsager() != null) { // tjek for ledsager
            prisUdflugter = deltager.getLedsager().getUdflugter().stream()
                    .mapToDouble(Udflugt::getPris)
                    .sum();
        }

        return prisKonference + prisHotel + prisServices + prisUdflugter; // total pris
    }
}
