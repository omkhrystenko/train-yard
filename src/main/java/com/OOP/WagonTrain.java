package com.OOP;

import java.util.ArrayList;
import java.util.List;

public class WagonTrain extends RailwayCar {
    private List<RailwayCar> wagonsChain;

    public WagonTrain(String wagonName) {
        super(wagonName, 0, 0, 0);
        wagonsChain = new ArrayList<RailwayCar>();
    }

    public void add(RailwayCar railwayCar) {
        wagonsChain.add(railwayCar);
    }

    @Override
    public int getSeatsNumber(){
        int count = 0;
        for (RailwayCar railwayCar: wagonsChain) {
            count += railwayCar.getSeatsNumber();
        }
        return count;
    }

    public int getClassOfComfort(){
        int commonStatus = 0;
        int count = 0;
        for (RailwayCar railwayCar: wagonsChain) {
            commonStatus += railwayCar.getSeatsNumber();
            count++;
        }
        return (int)commonStatus/count;
    }

    public int getLuggagePlaces(){
        int count = 0;
        for (RailwayCar railwayCar: wagonsChain) {
            count += railwayCar.getLuggagePlaces();
        }
        return count;
    }

    public List<RailwayCar> getWagonsChain() {
        return new ArrayList<>(wagonsChain);
    }
}
