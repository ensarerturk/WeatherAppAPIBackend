package org.example.mapper;

import org.example.dto.WeatherResponseDTO;
import org.example.model.WeatherData;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WeatherDataMapper {
    WeatherDataMapper INSTANCE = Mappers.getMapper(WeatherDataMapper.class);

    /**
     * Converts WeatherData object to WeatherResponseDTO.
     *
     * @param weatherData WeatherData object
     * @return WeatherResponseDTO object
     */
    WeatherResponseDTO toWeatherResponseDTO(WeatherData weatherData);

    /**
     * Converts WeatherData list to WeatherResponseDTO list.
     *
     * @param weatherDataList WeatherData list
     * @return WeatherResponseDTO list
     */
    List<WeatherResponseDTO> toWeatherResponseDTOList(List<WeatherData> weatherDataList);


}
