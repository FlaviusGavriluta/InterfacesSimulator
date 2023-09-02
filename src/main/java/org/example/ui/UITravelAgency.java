package org.example.ui;

import org.example.agency.Location;
import org.example.agency.Person;
import org.example.discount.Discountable;
import org.example.discount.SeniorDiscount;
import org.example.discount.YouthDiscount;
import org.example.travel.SummerPackage;
import org.example.travel.TravelPackage;
import org.example.travel.WinterPackage;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UITravelAgency {
    public void run() {

        List<Location> locations = new ArrayList<>();
        locations.add(new Location("Paris", "France", 1200.0, 9));
        locations.add(new Location("Tokyo", "Japan", 1500.0, 8));
        locations.add(new Location("New York", "USA", 1300.0, 10));
        locations.add(new Location("Sydney", "Australia", 1600.0, 7));
        locations.add(new Location("Rome", "Italy", 1300.0, 8));
        locations.add(new Location("Cairo", "Egypt", 900.0, 6));
        locations.add(new Location("Rio de Janeiro", "Brazil", 1200.0, 7));


        List<Person> clients = new ArrayList<>();
        clients.add(new Person("John", 63));
        clients.add(new Person("Jayson", 23));
        clients.add(new Person("Paul", 60));
        clients.add(new Person("Elisabeth", 33));
        clients.add(new Person("Evan", 18));
        clients.add(new Person("Levi", 43));

        List<TravelPackage> travelPackages = new ArrayList<>();
        locations.forEach(location -> {
            travelPackages.add(new SummerPackage(location.getName(), location.getInitialCost()));
            travelPackages.add(new WinterPackage(location.getName(), location.getInitialCost()));
        });

        // prima metoda de sortare
        Collections.sort(travelPackages, Comparator.comparing(TravelPackage::getInitialCost).thenComparing(TravelPackage::getDestination));
        // a doua metoda de sortare
        Comparator<TravelPackage> sortedByCostThenName = Comparator.comparing(TravelPackage::getInitialCost).thenComparing(TravelPackage::getDestination);
        travelPackages.sort(sortedByCostThenName.reversed());

        System.out.println("Sorted Travel Packages by Initial Cost:");
        travelPackages.forEach(System.out::println);

        Predicate<Location> isPopular = location -> location.getPopularity() > 7;

        List<Location> popularLocations = locations.stream()
                .filter(isPopular)
                .sorted(Comparator.comparing(Location::getPopularity)
                        .thenComparing(Location::getInitialCost)
                        .thenComparing(Location::getName))
                .collect(Collectors.toList());

        System.out.println("-----------------------------");
        popularLocations.forEach(System.out::println);

        Predicate<Person> isSenior = person -> person.getAge() > 59;
        Predicate<Person> isYouth = person -> person.getAge() > 17 && person.getAge() < 26;
        Comparator<Person> sortedByAgeThenName = Comparator.comparing(Person::getAge).thenComparing(Person::getName);

        List<Person> seniors = clients.stream().filter(isSenior).sorted(sortedByAgeThenName).collect(Collectors.toList());
        List<Person> youths = clients.stream().filter(isYouth).sorted(sortedByAgeThenName).collect(Collectors.toList());

        System.out.println("-----------------------------");
        seniors.forEach(System.out::println);
        System.out.println("-----------------------------");
        youths.forEach(System.out::println);
        System.out.println("-----------------------------");

        Discountable seniorDiscount = new SeniorDiscount();
        Discountable youthDiscount = new YouthDiscount();

        youths.forEach(client -> {
            travelPackages.forEach(travelPackage -> {
                double discountedCost = travelPackage.getDiscounted(youthDiscount, client.getAge());
                System.out.println(travelPackage.getDestination()
                        + "(" + travelPackage.getInitialCost() + "): "
                        + client.getName() + " - " + discountedCost);
            });
        });

        System.out.println("-----------------------------");

        seniors.forEach(client -> {
            travelPackages.forEach(travelPackage -> {
                double discountedCost = travelPackage.getDiscounted(seniorDiscount, client.getAge());
                System.out.println(travelPackage.getDestination()
                        + "(" + travelPackage.getInitialCost() + "): "
                        + client.getName() + " - " + discountedCost);
            });
        });
    }
}