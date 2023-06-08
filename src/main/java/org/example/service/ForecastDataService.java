package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.general.BaseEntityService;
import org.example.model.City;
import org.example.model.ForecastData;
import org.example.model.ForecastResponse;
import org.example.repository.ForecastDataRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class ForecastDataService extends BaseEntityService<ForecastData, ForecastDataRepository> {

    public ForecastDataService(ForecastDataRepository repository) {
        super(repository);
    }

    /**
     * Saves weather forecast data for the city.
     *
     * @param city City
     * @param forecast weather forecast
     * @return Kaydedilen weather forecast data
     */
    public ForecastData saveForecastData(City city, ForecastResponse.Forecast forecast) {
        log.info("Saving forecast data for city: {}", city.getName());

        Double temperatureCelsius = (double) Math.round(forecast.getMain().getTemp() - 273.15);

        ForecastData forecastData = new ForecastData();
        forecastData.setCityName(city.getName());
        forecastData.setCityId(city.getId());
        forecastData.setForecastTime(LocalDateTime.parse(forecast.getDt_txt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        forecastData.setTemperature(temperatureCelsius);

        ForecastData saveForcastData = save(forecastData);

        log.info("Forecast data saved successfully for city: {}", city.getName());

        return saveForcastData;
    }

}
