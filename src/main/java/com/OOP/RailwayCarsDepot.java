package com.OOP;

public class RailwayCarsDepot {
    public RailwayCar get(String wagonName) {
        switch(wagonName) {
            case ("Luxury"):
                return new RailwayCar(wagonName, 20, 1, 30);
            case ("Coupe"):
                return new RailwayCar(wagonName, 40, 2, 50);
            case ("Platskart"):
                return new RailwayCar(wagonName, 50, 3, 40);
            case ("Seated"):
                return new RailwayCar(wagonName, 60, 4, 35);
            case("Locomotive"):
                return new RailwayCar(wagonName, 0, 0, 0);
        }
        return null;
    }

}
