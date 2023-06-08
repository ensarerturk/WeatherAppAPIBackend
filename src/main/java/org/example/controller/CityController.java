package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.example.controller.contract.CityControllerContract;
import org.example.dto.ForecastDTO;
import org.example.dto.WeatherResponseDTO;
import org.example.general.restresponse.RestResponse;
import org.example.mapper.ForecastDataMapper;
import org.example.mapper.WeatherDataMapper;
import org.example.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cities")
@Api(tags = "City", description = "City Operation")
public class CityController {

    private final CityControllerContract controllerContract;

    public CityController(CityControllerContract controllerContract) {
        this.controllerContract = controllerContract;
    }

    @GetMapping("/{cityName}/weather")
    @ApiOperation("Get weather information for a specific city")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RestResponse.class),
            @ApiResponse(code = 404, message = "City Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<WeatherResponseDTO> getWeatherByCityName(@PathVariable String cityName,
                                                                   @RequestParam String username,
                                                                   @RequestParam String apiKey) {
        log.info("Getting weather for city: {}", cityName);

        WeatherResponseDTO weatherResponseDTO = controllerContract.getWeatherByCityName(cityName, username, apiKey);

        log.info("Weather retrieved successfully");

        return ResponseEntity.ok(weatherResponseDTO);
    }

    @GetMapping("/{cityName}/weather-forecast")
    @ApiOperation("Get weather forecasts for a specific city")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RestResponse.class),
            @ApiResponse(code = 404, message = "City Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<List<ForecastDTO>> getWeatherForecastByCityName(@PathVariable String cityName,
                                                                          @RequestParam String username,
                                                                          @RequestParam String apiKey) {
        log.info("Getting weather forecast for city: {}", cityName);

        List<ForecastDTO> forecastDTOList = controllerContract.getWeatherForecastByCityName(cityName, username, apiKey);

        log.info("Weather forecast retrieved successfully");

        return ResponseEntity.ok(forecastDTOList);
    }

    @GetMapping("/{username}/query-weathers")
    @ApiOperation("Get weather information of cities by username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RestResponse.class),
            @ApiResponse(code = 404, message = "User Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<List<WeatherResponseDTO>> getCityWeatherByUsername(@PathVariable String username) {
        log.info("Getting weather for user: {}", username);

        List<WeatherData> weatherDataList = controllerContract.getCityWeatherByUsername(username);

        List<WeatherResponseDTO> weatherResponseDTOList = WeatherDataMapper.INSTANCE.toWeatherResponseDTOList(weatherDataList);

        log.info("Weather retrieved successfully");

        return ResponseEntity.ok(weatherResponseDTOList);
    }


    @GetMapping("/{username}/query-weather-forecasts")
    @ApiOperation("Get weather forecasts for cities by username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RestResponse.class),
            @ApiResponse(code = 404, message = "User Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<List<ForecastDTO>> getWeatherForecastsByUsername(@PathVariable String username) {
        log.info("Getting weather forecasts for user: {}", username);

        List<ForecastData> forecasts = controllerContract.getWeatherForecastsByUsername(username);

        List<ForecastDTO> forecastDTOList = ForecastDataMapper.INSTANCE.toForecastDTOList(forecasts);

        log.info("Weather forecasts retrieved successfully");

        return ResponseEntity.ok(forecastDTOList);
    }
}