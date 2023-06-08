package org.example.mapper;

import org.example.dto.ForecastDTO;
import org.example.model.ForecastData;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ForecastDataMapper {
    ForecastDataMapper INSTANCE = Mappers.getMapper(ForecastDataMapper.class);

    /**
     * Converts ForecastData object to ForecastDTO.
     *
     * @param forecastData ForecastData object
     * @return ForecastDTO object
     */
    ForecastDTO toForecastDTO(ForecastData forecastData);

    /**
     * Converts ForecastData list to ForecastDTO list.
     *
     * @param forecastDataList ForecastData list
     * @return ForecastDTO list
     */
    List<ForecastDTO> toForecastDTOList(List<ForecastData> forecastDataList);
}
