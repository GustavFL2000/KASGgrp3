package model;

public class HotelReservation {
    private boolean isDoubleRoom;
    private double pris;
    private Hotel hotel;
    private Tilmelding tilmelding;

    public HotelReservation(boolean isDoubleRoom, double pris, Hotel hotel, Tilmelding tilmelding) {
        this.isDoubleRoom = isDoubleRoom;
        this.pris = pris;
        this.hotel = hotel;
        this.tilmelding = tilmelding;
    }

    public Tilmelding getTilmelding() {
        return tilmelding;
    }
}
