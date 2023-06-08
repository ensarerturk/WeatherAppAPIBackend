package org.example.service;

import org.example.general.handler.NotFoundException;
import org.example.model.City;
import org.example.model.ForecastData;
import org.example.model.ForecastResponse;
import org.example.repository.ForecastDataRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.*;

public class ForecastDataServiceTest {

    @Mock
    private ForecastDataRepository forecastDataRepository;

    private ForecastDataService forecastDataService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        forecastDataService = new ForecastDataService(forecastDataRepository);
    }

    @Test
    public void testSaveForecastData_Success() {
        City city = new City();
        city.setId(1L);
        city.setName("Samsun");

        ForecastResponse.Forecast forecast = new ForecastResponse.Forecast();
        forecast.setDt_txt("2023-05-30 12:00:00");
        forecast.setMain(new ForecastResponse.Forecast.Main());
        forecast.getMain().setTemp(25.0);

        ForecastData expectedForecastData = new ForecastData();
        expectedForecastData.setCityName(city.getName());
        expectedForecastData.setCityId(city.getId());
        expectedForecastData.setForecastTime(LocalDateTime.parse(forecast.getDt_txt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        expectedForecastData.setTemperature(forecast.getMain().getTemp());

        when(forecastDataRepository.save(any(ForecastData.class))).thenReturn(expectedForecastData);

        ForecastData savedForecastData = forecastDataService.saveForecastData(city, forecast);

        Assertions.assertEquals(expectedForecastData, savedForecastData);
        verify(forecastDataRepository, times(1)).save(any(ForecastData.class));
    }

    @Test
    public void testSaveForecastData_NullCity() {
        ForecastResponse.Forecast forecast = new ForecastResponse.Forecast();

        Assertions.assertThrows(NullPointerException.class, () -> {
            forecastDataService.saveForecastData(null, forecast);
        });
        verify(forecastDataRepository, never()).save(any());
    }

}