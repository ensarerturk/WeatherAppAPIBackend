package org.example.controller.contract.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.controller.contract.CityControllerContract;
import org.example.dto.ForecastDTO;
import org.example.dto.WeatherResponseDTO;
import org.example.general.handler.NotFoundException;
import org.example.model.*;
import org.example.service.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CityControllerContractImpl implements CityControllerContract {

    private final CityEntityService cityEntityService;
    private final WeatherServiceClient weatherServiceClient;
    private final UserEntityService userEntityService;
    private final ForecastDataService forecastDataService;
    private final WeatherDataService weatherDataService;

    public CityControllerContractImpl(CityEntityService cityEntityService, WeatherServiceClient weatherServiceClient,
                                      UserEntityService userEntityService, ForecastDataService forecastDataService,
                                      WeatherDataService weatherDataService) {
        this.cityEntityService = cityEntityService;
        this.weatherServiceClient = weatherServiceClient;
        this.userEntityService = userEntityService;
        this.forecastDataService = forecastDataService;
        this.weatherDataService = weatherDataService;
    }

    // Method to retrieve weather information for a particular city
    @Override
    public WeatherResponseDTO getWeatherByCityName(String cityName, String username, String apiKey) {
        log.info("Getting weather for city: {}", cityName);

        WeatherResponse weatherResponse = weatherServiceClient.getWeatherByCityName(cityName, apiKey);

        Long userId = getUserIdByUsername(username);

        City city = cityEntityService.saveWeatherCity(cityName, userId);

        WeatherData weatherData = weatherDataService.saveWeatherData(city, weatherResponse.getMain().getTemp());

        log.info("Weather retrieved successfully for city: {}", cityName);

        return new WeatherResponseDTO(weatherData.getWeatherTime() ,cityName, weatherData.getTemperature());
    }

    // Method to retrieve weather forecasts for a particular city
    @Override
    public List<ForecastDTO> getWeatherForecastByCityName(String cityName, String username, String apiKey) {
        log.info("Getting weather forecast for city: {}", cityName);

        ForecastResponse forecastResponse = weatherServiceClient.getWeatherForecastByCityName(cityName, apiKey);

        Long userId = getUserIdByUsername(username);

        City city = cityEntityService.saveForecastCity(cityName, userId);

        List<ForecastDTO> forecastDTOList = new ArrayList<>();

        for (ForecastResponse.Forecast forecast : forecastResponse.getList()) {
            ForecastData forecastData = forecastDataService.saveForecastData(city, forecast);

            ForecastDTO forecastDTO = new ForecastDTO(forecastData.getForecastTime(), forecastData.getTemperature(), city.getName());
            forecastDTOList.add(forecastDTO);
        }
        log.info("Weather forecast retrieved successfully for city: {}", cityName);

        return forecastDTOList;
    }

    // Method to fetch weather forecasts by username
    @Override
    public List<ForecastData> getWeatherForecastsByUsername(String username) {
        log.info("Getting weather forecasts for username: {}", username);
        return cityEntityService.getWeatherForecastsByUsername(username);
    }

    // Method to retrieve weather information of cities by username
    @Override
    public List<WeatherData> getCityWeatherByUsername(String username) throws NotFoundException {
        log.info("Getting city weather for username: {}", username);
        return cityEntityService.getCityWeatherByUsername(username);
    }

    private Long getUserIdByUsername(String username) {
        log.info("Getting user ıd for username: {}", username);
        User user = userEntityService.findUserByUsername(username);
        return user.getId();
    }
}







