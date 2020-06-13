package com.OOP;

public class RailwayCar implements RollingStock{
    private String wagonName;
    private int classOfComfort;
    private int seatsNumber;
    private int luggagePlaces;

    public RailwayCar(String wagonName, int seatsNumber, int classOfComfort, int luggagePlaces) {
        this.wagonName = wagonName;
        this.seatsNumber = seatsNumber;
        this.classOfComfort = classOfComfort;
        this.luggagePlaces = luggagePlaces;
    }


    @Override
    public String getWagonName() {
        return wagonName;
    }

    @Override
    public int getClassOfComfort() {
        return classOfComfort;
    }

    @Override
    public int getSeatsNumber() {
        return seatsNumber;
    }

    @Override
    public int getLuggagePlaces() {
        return luggagePlaces;
    }
}
