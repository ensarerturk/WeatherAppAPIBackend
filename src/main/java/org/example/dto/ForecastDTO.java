package org.example.dto;

import java.time.LocalDateTime;

public record ForecastDTO(LocalDateTime forecastTime,
                          double temperature,
                          String cityName) {
}
