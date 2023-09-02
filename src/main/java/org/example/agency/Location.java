package org.example.agency;

public class Location {
    private String name;
    private String country;
    private double initialCost;
    private int popularity;

    public Location(String name, String country, double initialCost, int popularity) {
        this.name = name;
        this.country = country;
        this.initialCost = initialCost;
        this.popularity = popularity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getInitialCost() {
        return initialCost;
    }

    public void setInitialCost(double initialCost) {
        this.initialCost = initialCost;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", initialCost=" + initialCost +
                ", popularity=" + popularity +
                '}';
    }
}
