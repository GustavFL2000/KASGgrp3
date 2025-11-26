package model;

import java.util.ArrayList;

public class HotelReservation {
    private boolean isDoubleRoom;
    private Hotel hotel;
    private Tilmelding tilmelding;
    private ArrayList<Service> services = new ArrayList<>();

    public HotelReservation(boolean isDoubleRoom, Hotel hotel, Tilmelding tilmelding) {
        this.isDoubleRoom = isDoubleRoom;
        this.hotel = hotel;
        this.tilmelding = tilmelding;
        if (hotel != null) hotel.addReservation(this);
    }

    // Gettere

    public Hotel getHotel() { return hotel; }
    public Tilmelding getTilmelding() { return tilmelding; }
    public ArrayList<Service> getServices() { return new ArrayList<>(services); }

    // Relationer
    public void addService(Service s) {
        if (!services.contains(s)) {
            services.add(s);
        }
    }

    // beregn pris eksempel
    public double beregnPris() {

        // antal nætter = afrejse - ankomst
        int nights = tilmelding.getAfrejseDato().getDayOfYear()
                - tilmelding.getAnkomstDato().getDayOfYear();

        // base room price pr. nat
        double roomPrice = isDoubleRoom ? hotel.getPrisDobbelt() : hotel.getPrisEnkelt();

        // servicepris pr. nat
        double servicePris = services.stream()
                .mapToDouble(Service::getPris)
                .sum();

        return (roomPrice * nights) + (servicePris * nights);
    }

}
