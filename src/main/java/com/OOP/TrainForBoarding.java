package com.OOP;

public class TrainForBoarding extends WagonTrain {
    public TrainForBoarding(String locomotiveName, RailwayCar locomotive) {
        super(locomotiveName);
        super.add(locomotive);
    }
}
