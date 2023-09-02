package org.example.agency;

import org.example.travel.TravelPackage;

import java.util.ArrayList;
import java.util.List;

public class TravelAgency {
    private List<TravelPackage> travelPackages;
    private List<Location> locations;
    private List<Person> clients;

    public TravelAgency() {
        this.travelPackages = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    public List<TravelPackage> getTravelPackages() {
        return travelPackages;
    }

    public void setTravelPackages(List<TravelPackage> travelPackages) {
        this.travelPackages = travelPackages;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Person> getClients() {
        return clients;
    }

    public void setClients(List<Person> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "TravelAgency{" +
                "travelPackages=" + travelPackages +
                ", locations=" + locations +
                ", clients=" + clients +
                '}';
    }
}