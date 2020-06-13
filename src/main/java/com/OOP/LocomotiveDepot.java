package com.OOP;

public class LocomotiveDepot {
    private RailwayCarsDepot railwayCarsDepot;

    public LocomotiveDepot(RailwayCarsDepot railwayCarsDepot) {
        this.railwayCarsDepot = railwayCarsDepot;
    }

    public WagonTrain get(String trainName) {
                return new TrainForBoarding(trainName, railwayCarsDepot.get("Locomotive"));
        }




}
