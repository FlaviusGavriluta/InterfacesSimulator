package org.example.travel;

import org.example.discount.Discountable;

public class SummerPackage implements TravelPackage {
    private String destination;
    private double initialCost;

    public SummerPackage(String destination, double initialCost) {
        this.destination = destination;
        this.initialCost = initialCost;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public double getInitialCost() {
        return initialCost;
    }

    @Override
    public double getDiscounted(Discountable discountable, int age) {
        return discountable.calculateDiscount(initialCost, age, destination);
    }

    @Override
    public String toString() {
        return "SummerPackage{" +
                "destination='" + destination + '\'' +
                ", initialCost=" + initialCost +
                '}';
    }
}
