package model;

import java.util.ArrayList;

public class Hotel {
    private String navn;
    private String adresse;
    private String beskrivelse;
    private ArrayList<Service> services = new ArrayList<>();
    private ArrayList<HotelReservation> reservations = new ArrayList<>();

    public Hotel(String navn, String adresse, String beskrivelse) {
        this.navn = navn;
        this.adresse = adresse;
        this.beskrivelse = beskrivelse;
    }

    public Service createService(String navn, double pris) {
        Service service = new Service(navn, pris);
        services.add(service);
        return service;
    }

    public ArrayList<HotelReservation> getReservations() {
        return new ArrayList<>(reservations);
    }

    public void addHotelReservation(HotelReservation reservation) {
        if (!reservations.contains(reservation)) {
            reservations.add(reservation);
        }
    }

    public void removeHotelReservation(HotelReservation reservation) {
        reservations.remove(reservation);
    }

    public String getNavn() {
        return navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "navn='" + navn + '\'' +
                ", adresse='" + adresse + '\'' +
                ", beskrivelse='" + beskrivelse + '\'' +
                ", services=" + services +
                ", reservations=" + reservations +
                '}';
    }
}
