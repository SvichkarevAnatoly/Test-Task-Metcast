package ru.svichkarev.metcast.model.parameter;

public class Pressure {
    private int min, max;

    public Pressure(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public String toString() {
        return "" + (max + min) / 2;
    }
}
