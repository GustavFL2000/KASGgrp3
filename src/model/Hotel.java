package model;

import java.util.ArrayList;

public class Hotel {
    private String navn;
    private String adresse;
    private String beskrivelse;
    private double prisEnkelt;
    private double prisDobbelt;
    private ArrayList<Service> services = new ArrayList<>();
    private ArrayList<HotelReservation> reservations = new ArrayList<>();

    public Hotel(String navn, String adresse, String beskrivelse, double prisEnkelt, double prisDobbelt) {
        this.navn = navn;
        this.adresse = adresse;
        this.beskrivelse = beskrivelse;
        this.prisEnkelt = prisEnkelt;
        this.prisDobbelt = prisDobbelt;
    }

    // Gettere
    public String getNavn() { return navn; }
    public double getPrisEnkelt() { return prisEnkelt; }
    public double getPrisDobbelt() { return prisDobbelt; }
    public ArrayList<Service> getServices() { return new ArrayList<>(services); }

    // Relationer
    public void addReservation(HotelReservation r) {
        if (!reservations.contains(r)) {
            reservations.add(r);
        }
    }

    public void addService(Service s) {
        if (!services.contains(s)) {
            services.add(s);
        }
    }

    // create service (domain)
    public Service createService(String navn, double pris) {
        Service service = new Service(navn, pris);
        this.addService(service);
        return service;
    }

    @Override
    public String toString() {
        return navn;
    }

}
