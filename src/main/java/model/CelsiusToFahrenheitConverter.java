package model;

public class CelsiusToFahrenheitConverter implements Converter {
    @Override
    public double convert(double temperature) {
        return (temperature * 9.0 / 5.0) + 32;
    }
}
