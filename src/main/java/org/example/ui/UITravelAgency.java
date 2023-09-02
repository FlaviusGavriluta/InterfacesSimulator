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

        sortedList(travelPackages, Comparator.comparing(TravelPackage::getInitialCost).thenComparing(TravelPackage::getDestination));
        System.out.println("Sorted Travel Packages by Initial Cost:");
        travelPackages.forEach(System.out::println);
        System.out.println("-----------------------------");

        Predicate<Location> isPopular = location -> location.getPopularity() > 7;
        Comparator<Location> locationComparator = Comparator.comparing(Location::getPopularity).thenComparing(Location::getInitialCost).thenComparing(Location::getName);
        List<Location> popularLocations = filterAndSort(locations, isPopular, locationComparator);
        popularLocations.forEach(System.out::println);
        System.out.println("-----------------------------");


        Predicate<Person> isSenior = person -> person.getAge() > 59;
        Predicate<Person> isYouth = person -> person.getAge() > 17 && person.getAge() < 26;
        Comparator<Person> sortedByAgeThenName = Comparator.comparing(Person::getAge).thenComparing(Person::getName);

        List<Person> seniors = filterAndSort(clients, isSenior, sortedByAgeThenName);
        List<Person> youths = filterAndSort(clients, isYouth, sortedByAgeThenName);

        System.out.println("-----------------------------");
        seniors.forEach(System.out::println);
        System.out.println("-----------------------------");
        youths.forEach(System.out::println);
        System.out.println("-----------------------------");

        Discountable seniorDiscount = new SeniorDiscount();
        Discountable youthDiscount = new YouthDiscount();

        applyDiscounts(youths, travelPackages, new YouthDiscount());
        applyDiscounts(seniors, travelPackages, new SeniorDiscount());
    }

    public <T> void sortedList(List<T> list, Comparator<T> comparator) {
        list.sort(comparator);
    }

    public <T> List<T> filterAndSort(List<T> list, Predicate<T> predicate, Comparator<T> comparator) {
        return list.stream()
                .filter(predicate)
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public void applyDiscounts(List<Person> clients, List<TravelPackage> travelPackages, Discountable discountStrategy) {
        clients.forEach(client -> {
            travelPackages.forEach(travelPackage -> {
                double discountedCost = travelPackage.getDiscounted(discountStrategy, client.getAge());
                System.out.println(travelPackage.getDestination()
                        + "(" + travelPackage.getInitialCost() + "): "
                        + client.getName() + " - " + discountedCost);
            });
        });
    }
}