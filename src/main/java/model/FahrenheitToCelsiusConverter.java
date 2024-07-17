package model;

public class FahrenheitToCelsiusConverter implements Converter {
    @Override
    public double convert(double temperature) {
        return (temperature - 32) * 5.0 / 9.0;
    }

    @Override
    public String toString() {
        return "Fahrenheit";
    }
}
