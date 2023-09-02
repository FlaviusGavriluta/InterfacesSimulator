package org.example.discount;

public class SeniorDiscount implements Discountable {
    @Override
    public double calculateDiscount(double initialCost, int age, String location) {
        if (age >= 60 && location.equals("Rio de Janeiro"))
            return initialCost * 0.65; // 35% discount
        return initialCost;
    }
}