package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.general.BaseEntityService;
import org.example.general.handler.NotFoundException;
import org.example.general.handler.UserNotFoundException;
import org.example.model.City;
import org.example.model.WeatherData;
import org.example.model.ForecastData;
import org.example.model.User;
import org.example.repository.CityRepository;
import org.example.repository.WeatherDataRepository;
import org.example.repository.ForecastDataRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CityEntityService extends BaseEntityService<City, CityRepository> {

    private final UserRepository userRepository;
    private final ForecastDataRepository forecastDataRepository;
    private final CityRepository cityRepository;
    private final WeatherDataRepository weatherDataRepository;

    public CityEntityService(CityRepository repository, UserRepository userRepository, ForecastDataRepository forecastDataRepository, CityRepository cityRepository, WeatherDataRepository weatherDataRepository) {
        super(repository);
        this.userRepository = userRepository;
        this.forecastDataRepository = forecastDataRepository;
        this.cityRepository = cityRepository;
        this.weatherDataRepository = weatherDataRepository;
    }

    /**
     * Saves the city in the database.
     *
     * @param city City to save
     * @return Saved city
     */
    private City saveCity(City city) {
        return cityRepository.save(city);
    }

    /**
     * Saves the city for the weather.
     *
     * @param cityName City name
     * @param userId User ID
     * @return Saved city
     */
    public City saveWeatherCity(String cityName, Long userId) {
        log.info("Saving weather city: {}", cityName);

        City city = new City();
        city.setName(cityName);
        city.setUserId(userId);

        City savedCity = saveCity(city);

        log.info("Weather city saved successfully: {}", cityName);

        return savedCity;
    }

    /**
     * Saves the city for forecast.
     *
     * @param cityName City name
     * @param userId User ID
     * @return Saved city
     */
    public City saveForecastCity(String cityName, Long userId) {
        log.info("Saving forecast city: {}", cityName);

        City city = new City();
        city.setName(cityName);
        city.setUserId(userId);

        City savedCity = saveCity(city);

        log.info("Forecast city saved successfully: {}", cityName);

        return savedCity;
    }

    /**
     * Brings city information by city name.
     *
     * @param cityName City name
     * @return City info
     */
    public City getCityByName(String cityName) {
        return cityRepository.findByName(cityName);
    }

    /**
     * Brings weather forecasts by username.
     *
     * @param username Username
     * @return Weather forecasts
     * @throws UserNotFoundException Throws when user not found
     */
    public List<ForecastData> getWeatherForecastsByUsername(String username) {
        log.info("Getting weather forecasts for username: {}", username);

        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User not found");
            throw new UserNotFoundException();
        }

        List<City> cities = cityRepository.findByUserId(user.getId());
        List<ForecastData> forecasts = new ArrayList<>();
        for (City city : cities) {
            List<ForecastData> cityForecasts = forecastDataRepository.findByCityId(city.getId());
            forecasts.addAll(cityForecasts);
        }
        log.info("Weather forecasts retrieved successfully for username: {}", username);

        return forecasts;
    }

    /**
     * Brings city weather by username.
     *
     * @param username Username
     * @return City weather
     * @throws NotFoundException Throws when user not found
     */
    public List<WeatherData> getCityWeatherByUsername(String username) throws NotFoundException {
        log.info("Getting city weather for username: {}", username);

        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User not found");
            throw new UserNotFoundException();
        }

        List<City> cities = cityRepository.findByUserId(user.getId());
        List<WeatherData> weatherDataList = new ArrayList<>();
        for (City city : cities) {
            List<WeatherData> weatherDataEntries = weatherDataRepository.findByCityId(city.getId());
            weatherDataList.addAll(weatherDataEntries);
        }
        log.info("City weather retrieved successfully for username: {}", username);

        return weatherDataList;
    }
}
