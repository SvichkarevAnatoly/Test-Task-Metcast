package ru.svichkarev.metcast.model.parameter;

public class Relwet {
    private int min, max;

    public Relwet(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public String toString() {
        return "" + (max + min) / 2;
    }
}
