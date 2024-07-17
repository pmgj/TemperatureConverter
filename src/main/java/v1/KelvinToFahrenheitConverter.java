package v1;

public class KelvinToFahrenheitConverter implements Converter {
    @Override
    public double convert(double temperature) {
        return (temperature - 273.15) * 9.0 / 5.0 + 32;
    }
}
