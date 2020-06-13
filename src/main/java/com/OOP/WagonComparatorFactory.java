package com.OOP;


import java.util.Comparator;

public class WagonComparatorFactory {
    public static Comparator<RailwayCar> get(String id) {
        switch (id) {
            case "seats number":
                return new com.OOP.SeatsNumberComparator();

            case "class of comfort":
                return new Comparator<RailwayCar>() {
                    @Override
                    public int compare(RailwayCar f1, RailwayCar f2) {
                        return Integer.compare(f1.getClassOfComfort(), f2.getClassOfComfort());
                    }
                };

        }
        return null;
    }
}

class SeatsNumberComparator implements Comparator<RailwayCar> {
    @Override
    public int compare(RailwayCar f1, RailwayCar f2) {
        return Integer.compare(f1.getSeatsNumber(), f2.getSeatsNumber());
    }
}