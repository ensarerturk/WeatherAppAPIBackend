package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.general.BaseEntityService;
import org.example.model.City;
import org.example.model.WeatherData;
import org.example.repository.WeatherDataRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class WeatherDataService extends BaseEntityService<WeatherData, WeatherDataRepository> {
    public WeatherDataService(WeatherDataRepository repository) {
        super(repository);
    }

    /**
     * Saves weather data for a specific city.
     *
     * @param city City
     * @param temperatureFahrenheit Air temperature in Fahrenheit
     * @return Recorded weather data
     */
    public WeatherData saveWeatherData(City city, Double temperatureFahrenheit) {
        log.info("Saving weather data for city: {}", city.getName());

        // Convert Fahrenheit to Celsius
        Double temperatureCelsius = (double) Math.round(temperatureFahrenheit - 273.15);

        WeatherData weatherData = new WeatherData();
        weatherData.setCityName(city.getName());
        weatherData.setCityId(city.getId());
        weatherData.setTemperature(temperatureCelsius);
        weatherData.setWeatherTime(LocalDateTime.now());

        WeatherData savedWeatherData = save(weatherData);

        log.info("Weather data saved successfully for city: {}", city.getName());

        return savedWeatherData;
    }
}
