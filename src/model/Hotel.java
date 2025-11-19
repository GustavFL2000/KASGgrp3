package model;

import java.util.ArrayList;

public class Hotel {

    private String name;
    private String adresse;
    private String beskrivelse;
    private ArrayList<Service> services = new ArrayList<>();
    private ArrayList<HotelReservation> reservations = new ArrayList<>();

    public Hotel(String name, String adresse, String beskrivelse) {
        this.name = name;
        this.adresse = adresse;
        this.beskrivelse = beskrivelse;
    }

    public Service createService(Boolean wifi, Boolean mad, Boolean bad) {
        Service service = new Service(wifi, mad, bad);
        services.add(service);
        return service;
    }

    public String getName() {
        return name;
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
                "name='" + name + '\'' +
                ", adresse='" + adresse + '\'' +
                ", beskrivelse='" + beskrivelse + '\'' +
                ", services=" + services +
                ", reservations=" + reservations +
                '}';
    }
}
