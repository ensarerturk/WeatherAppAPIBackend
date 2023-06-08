package org.example.controller.contract;

import org.example.dto.ForecastDTO;
import org.example.dto.WeatherResponseDTO;
import org.example.model.WeatherData;
import org.example.model.ForecastData;

import java.util.List;



public interface CityControllerContract {

    /**
     * Retrieves weather information for a specific city.
     *
     * @param cityName The name of the city
     * @param username The username
     * @param apiKey The API key
     * @return DTO object representing the weather response
     */
    WeatherResponseDTO getWeatherByCityName(String cityName, String username, String apiKey);

    /**
     * Retrieves weather forecasts for a specific city.
     *
     * @param cityName The name of the city
     * @param username The username
     * @param apiKey The API key
     * @return List of DTO objects representing the weather forecasts
     */
    List<ForecastDTO> getWeatherForecastByCityName(String cityName, String username, String apiKey);

    /**
     * Retrieves weather forecasts for cities associated with a specific username.
     *
     * @param username The username
     * @return List of forecast data objects
     */
    List<ForecastData> getWeatherForecastsByUsername(String username);

    /**
     * Retrieves weather information for cities associated with a specific username.
     *
     * @param username The username
     * @return List of weather data objects
     */
    List<WeatherData> getCityWeatherByUsername(String username);

}
