package org.example.travel;

import org.example.discount.Discountable;

public interface TravelPackage {
    String getDestination();

    double getInitialCost();

    double getDiscounted(Discountable discountable, int age);
}
