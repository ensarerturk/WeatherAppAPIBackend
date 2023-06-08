package org.example.service;

import org.junit.jupiter.api.Test;
import org.example.model.City;
import org.example.model.WeatherData;
import org.example.repository.WeatherDataRepository;
import org.example.service.WeatherDataService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class WeatherDataServiceTest {

    @Mock
    private WeatherDataRepository weatherDataRepository;

    private WeatherDataService weatherDataService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        weatherDataService = new WeatherDataService(weatherDataRepository);
    }

    @Test
    public void testSaveWeatherData() {
        // Hazırlık
        City city = new City();
        city.setName("Samsun");
        city.setId(1L);

        Double temperature = 25.5;

        WeatherData weatherDataToSave = new WeatherData();
        weatherDataToSave.setCityName(city.getName());
        weatherDataToSave.setCityId(city.getId());
        weatherDataToSave.setTemperature(temperature);
        weatherDataToSave.setWeatherTime(LocalDateTime.now());

        when(weatherDataRepository.save(any(WeatherData.class))).thenReturn(weatherDataToSave);

        // Çalıştırma
        WeatherData savedWeatherData = weatherDataService.saveWeatherData(city, temperature);

        // Doğrulama
        verify(weatherDataRepository, times(1)).save(any(WeatherData.class));
        Assertions.assertEquals(city.getName(), savedWeatherData.getCityName());
        Assertions.assertEquals(city.getId(), savedWeatherData.getCityId());
        Assertions.assertEquals(temperature, savedWeatherData.getTemperature());
    }
}