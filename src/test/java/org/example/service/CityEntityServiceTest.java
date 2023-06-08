package org.example.service;

import org.example.general.handler.NotFoundException;
import org.example.model.City;
import org.example.model.ForecastData;
import org.example.model.User;
import org.example.model.WeatherData;
import org.example.repository.CityRepository;
import org.example.repository.ForecastDataRepository;
import org.example.repository.UserRepository;
import org.example.repository.WeatherDataRepository;
import org.example.service.CityEntityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CityEntityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ForecastDataRepository forecastDataRepository;

    @Mock
    private WeatherDataRepository weatherDataRepository;

    private CityEntityService cityEntityService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        cityEntityService = new CityEntityService(cityRepository, userRepository, forecastDataRepository, cityRepository, weatherDataRepository);
    }

    @Test
    public void testSaveWeatherCity_Success() {
        String cityName = "New York";
        Long userId = 1L;
        City city = new City();
        city.setName(cityName);
        city.setUserId(userId);

        when(cityRepository.save(ArgumentMatchers.any(City.class))).thenReturn(city);

        City savedCity = cityEntityService.saveWeatherCity(cityName, userId);

        Assertions.assertEquals(cityName, savedCity.getName());
        Assertions.assertEquals(userId, savedCity.getUserId());
        verify(cityRepository, times(1)).save(ArgumentMatchers.any(City.class));
    }

    @Test
    public void testSaveForecastCity_Success() {
        String cityName = "London";
        Long userId = 2L;
        City city = new City();
        city.setName(cityName);
        city.setUserId(userId);

        when(cityRepository.save(ArgumentMatchers.any(City.class))).thenReturn(city);

        City savedCity = cityEntityService.saveForecastCity(cityName, userId);

        Assertions.assertEquals(cityName, savedCity.getName());
        Assertions.assertEquals(userId, savedCity.getUserId());
        verify(cityRepository, times(1)).save(ArgumentMatchers.any(City.class));
    }

    @Test
    public void testGetCityByName_Success() {
        String cityName = "Paris";
        City expectedCity = new City();
        expectedCity.setName(cityName);

        when(cityRepository.findByName(cityName)).thenReturn(expectedCity);

        City actualCity = cityEntityService.getCityByName(cityName);

        Assertions.assertEquals(expectedCity, actualCity);
        verify(cityRepository, times(1)).findByName(cityName);
    }

    @Test
    public void testGetWeatherForecastsByUsername_Success() {
        String username = "johnDoe";
        User user = new User();
        user.setUsername(username);
        user.setId(1L);
        List<City> cities = new ArrayList<>();
        City city1 = new City();
        city1.setId(1L);
        City city2 = new City();
        city2.setId(2L);
        cities.add(city1);
        cities.add(city2);
        List<ForecastData> expectedForecasts = new ArrayList<>();
        ForecastData forecast1 = new ForecastData();
        forecast1.setCityId(1L);
        ForecastData forecast2 = new ForecastData();
        forecast2.setCityId(2L);
        expectedForecasts.add(forecast1);
        expectedForecasts.add(forecast2);

        when(userRepository.findByUsername(username)).thenReturn(user);
        when(cityRepository.findByUserId(user.getId())).thenReturn(cities);
        when(forecastDataRepository.findByCityId(anyLong())).thenReturn(expectedForecasts);

        List<ForecastData> actualForecasts = cityEntityService.getWeatherForecastsByUsername(username);

        Assertions.assertEquals(expectedForecasts, actualForecasts);
        verify(userRepository, times(1)).findByUsername(username);
        verify(cityRepository, times(1)).findByUserId(user.getId());
        verify(forecastDataRepository, times(2)).findByCityId(anyLong());
    }

    @Test
    public void testGetWeatherForecastsByUsername_UserNotFound() {
        String username = "johnDoe";
        User user = null;

        when(userRepository.findByUsername(username)).thenReturn(user);

        Assertions.assertThrows(NotFoundException.class, () -> {
            cityEntityService.getWeatherForecastsByUsername(username);
        });
        verify(userRepository, times(1)).findByUsername(username);
        verify(cityRepository, never()).findByUserId(anyLong());
        verify(forecastDataRepository, never()).findByCityId(anyLong());
    }

    @Test
    public void testGetCityWeatherByUsername_Success() {
        String username = "johnDoe";
        User user = new User();
        user.setUsername(username);
        user.setId(1L);
        List<City> cities = new ArrayList<>();
        City city1 = new City();
        city1.setId(1L);
        City city2 = new City();
        city2.setId(2L);
        cities.add(city1);
        cities.add(city2);
        List<WeatherData> expectedWeatherDataList1 = new ArrayList<>();
        WeatherData weatherData1 = new WeatherData();
        weatherData1.setCityId(1L);
        expectedWeatherDataList1.add(weatherData1);
        List<WeatherData> expectedWeatherDataList2 = new ArrayList<>();
        WeatherData weatherData2 = new WeatherData();
        weatherData2.setCityId(2L);
        expectedWeatherDataList2.add(weatherData2);

        when(userRepository.findByUsername(username)).thenReturn(user);
        when(cityRepository.findByUserId(user.getId())).thenReturn(cities);
        when(weatherDataRepository.findByCityId(1L)).thenReturn(expectedWeatherDataList1);
        when(weatherDataRepository.findByCityId(2L)).thenReturn(expectedWeatherDataList2);

        List<WeatherData> actualWeatherDataList = cityEntityService.getCityWeatherByUsername(username);

        Assertions.assertEquals(expectedWeatherDataList1.size() + expectedWeatherDataList2.size(), actualWeatherDataList.size());
        verify(userRepository, times(1)).findByUsername(username);
        verify(cityRepository, times(1)).findByUserId(user.getId());
        verify(weatherDataRepository, times(1)).findByCityId(1L);
        verify(weatherDataRepository, times(1)).findByCityId(2L);
    }

    @Test
    public void testGetCityWeatherByUsername_UserNotFound() {
        String username = "johnDoe";
        User user = null;

        when(userRepository.findByUsername(username)).thenReturn(user);

        Assertions.assertThrows(NotFoundException.class, () -> {
            cityEntityService.getCityWeatherByUsername(username);
        });
        verify(userRepository, times(1)).findByUsername(username);
        verify(cityRepository, never()).findByUserId(anyLong());
        verify(weatherDataRepository, never()).findByCityId(anyLong());
    }
}