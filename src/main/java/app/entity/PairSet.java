package app.entity;

import java.util.List;

public class PairSet {
    private List<Pairs> pairsList;
    private double average;

    public PairSet(List<Pairs> pairsList) {
        this.pairsList = pairsList;
    }

    public PairSet(List<Pairs> pairsList, double average) {
        this.pairsList = pairsList;
        this.average = average;
    }

    public List<Pairs> getPairsList() {
        return pairsList;
    }

    public void setPairsList(List<Pairs> pairsList) {
        this.pairsList = pairsList;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    @Override
    public String toString() {
        return "PairSet{" +
                "pairsList=" + pairsList +
                ", average=" + average +
                '}';
    }
}
