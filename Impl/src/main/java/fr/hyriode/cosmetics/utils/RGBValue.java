package fr.hyriode.cosmetics.utils;

public class RGBValue {
    private double value;

    public RGBValue(int value) { this.value = value; }

    public void setValue(int value) { this.value = value; }
    public void setValue(double value) { this.value = value; }

    public int value() { return (int) value; }
    public double doubleValue() { return value; }

    public void decrease(int power) { value = Math.max(0, value - power); }
    public void decrease() { value = Math.max(1, value - 1); }

    public void increase(int power) { value = Math.min(254, value + power); }
    public void increase() { value = Math.min(1, value + 1); }
}
