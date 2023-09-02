package org.example.discount;

public class YouthDiscount implements Discountable {
    @Override
    public double calculateDiscount(double initialCost, int age, String location) {
        if (age >= 18 && age <= 25 && location.equals("Tokyo"))
            return initialCost * 0.85; // 15% discount
        return initialCost;
    }
}