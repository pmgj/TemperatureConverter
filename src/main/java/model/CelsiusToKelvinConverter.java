package model;

public class CelsiusToKelvinConverter implements Converter {
    @Override
    public double convert(double temperature) {
        return temperature + 273.15;
    }
}
