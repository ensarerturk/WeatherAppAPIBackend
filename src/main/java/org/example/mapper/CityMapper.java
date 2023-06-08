package org.example.mapper;

import org.example.dto.CityDTO;
import org.example.dto.CitySaveDTO;
import org.example.dto.ForecastDTO;
import org.example.dto.WeatherResponseDTO;
import org.example.model.City;
import org.example.model.ForecastResponse;
import org.example.model.WeatherData;
import org.example.model.WeatherResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    City toEntity(CitySaveDTO citySaveDTO);

    CityDTO toDTO(City city);

    ForecastDTO toForecastDTO(ForecastResponse.Forecast forecast);

    WeatherResponseDTO mapToWeatherResponseDTO(WeatherData weatherData);

}
