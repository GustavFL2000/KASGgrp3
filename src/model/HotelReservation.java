package model;

import java.util.ArrayList;

public class HotelReservation {
    private boolean isDoubleRoom;
    private double pris;
    private Hotel hotel;
    private Tilmelding tilmelding;
    private final ArrayList<Service> services = new ArrayList<>();

    public HotelReservation(boolean isDoubleRoom, double pris, Hotel hotel, Tilmelding tilmelding) {
        this.isDoubleRoom = isDoubleRoom;
        this.pris = pris;
        this.hotel = hotel;
        this.tilmelding = tilmelding;
    }

    public ArrayList<Service> getServices() {
        return new ArrayList<>(services);
    }

    public void addService(Service service) {
        if (!services.contains(service)) {
            services.add(service);
        }
    }

    public double getPris() {
        return pris;
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
