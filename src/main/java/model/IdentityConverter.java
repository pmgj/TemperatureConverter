package model;

public class IdentityConverter implements Converter {
    @Override
    public double convert(double temperature) {
        return temperature;
    }
}
