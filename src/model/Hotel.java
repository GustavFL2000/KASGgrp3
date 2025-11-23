package model;

import storage.Storage;

import java.util.ArrayList;

public class Hotel {
    private String navn;
    private String adresse;
    private String beskrivelse;
    private double enkeltVærelsesPris;
    private double dobbeltVærelsesPris;
    private ArrayList<Service> services = new ArrayList<>();
    private ArrayList<HotelReservation> reservations = new ArrayList<>();

    public Hotel(String navn, String adresse, String beskrivelse, double enkeltVærelsesPris, double dobbeltVærelsesPris) {
        this.navn = navn;
        this.adresse = adresse;
        this.beskrivelse = beskrivelse;
        this.enkeltVærelsesPris = enkeltVærelsesPris;
        this.dobbeltVærelsesPris = dobbeltVærelsesPris;
    }

    public static Hotel create(String name, String adresse, String beskrivelse, double enkeltVærelsesPris, double dobbeltVærelsesPris) {
        Hotel hotel = new Hotel(name, adresse, beskrivelse, enkeltVærelsesPris, dobbeltVærelsesPris);
        storage.Storage.addHotel(hotel);
        return hotel;
    }

    public double getEnkeltVærelsesPris() {
        return enkeltVærelsesPris;
    }

    public double getDobbeltVærelsesPris() {
        return dobbeltVærelsesPris;
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
