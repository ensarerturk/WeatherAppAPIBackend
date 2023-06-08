package org.example.service;

import org.example.model.ForecastResponse;
import org.example.model.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "weather-service", url = "https://api.openweathermap.org")
public interface WeatherServiceClient {

    /**
     * Retrieves weather information for a particular city.
     *
     * @param cityName City name
     * @param apiKey API key
     * @return Weather info
     */
    @GetMapping("/data/2.5/weather")
    WeatherResponse getWeatherByCityName(@RequestParam("q") String cityName,
                                         @RequestParam("appid") String apiKey);

    /**
     * Retrieves weather forecasts for a particular city.
     *
     * @param cityName City name
     * @param apiKey API key
     * @return Weather forecasts
     */
    @GetMapping("/data/2.5/forecast")
    ForecastResponse getWeatherForecastByCityName(@RequestParam("q") String cityName,
                                                  @RequestParam("appid") String apiKey);

}

