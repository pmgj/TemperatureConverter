package model;

public class FahrenheitToKelvinConverter implements Converter {
    @Override
    public double convert(double temperature) {
        return (temperature - 32) * 5.0 / 9.0 + 273.15;
    }
}
