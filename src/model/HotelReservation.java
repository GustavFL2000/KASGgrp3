package model;

import java.util.ArrayList;

public class HotelReservation {
    private boolean isDoubleRoom;
    private Hotel hotel;
    private Tilmelding tilmelding;
    private final ArrayList<Service> services = new ArrayList<>();

    public HotelReservation(boolean isDoubleRoom, Hotel hotel, Tilmelding tilmelding) {
        this.isDoubleRoom = isDoubleRoom;
        this.hotel = hotel;
        this.tilmelding = tilmelding;
    }

    public ArrayList<Service> getServices() {
        return new ArrayList<>(services);
    }

    public void addService(Service service) {
        if (!services.contains(service)) {
            // Ensure the service is actually offered by the hotel
            if (hotel.getServices().contains(service)) {
                services.add(service);
            }
        }
    }

    public double beregnPris() {
        double totalPris = 0;

        // Base room price
        double værelsesPris = isDoubleRoom ? hotel.getDobbeltVærelsesPris() : hotel.getEnkeltVærelsesPris();

        // Calculate number of nights from Tilmelding
        long antalNætter = tilmelding.getAnkomstDato().until(tilmelding.getAfrejseDato()).getDays();
        totalPris += værelsesPris * antalNætter;

        // Add price of selected services
        for (Service service : services) {
            totalPris += service.getPris();
        }

        return totalPris;
    }

    public Tilmelding getTilmelding() {
        return tilmelding;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public boolean isDoubleRoom() {
        return isDoubleRoom;
    }
}
