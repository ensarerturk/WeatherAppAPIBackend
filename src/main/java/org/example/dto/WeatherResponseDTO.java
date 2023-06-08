package org.example.dto;

import lombok.Setter;

import java.time.LocalDateTime;

public record WeatherResponseDTO(LocalDateTime weatherTime,
                                 String cityName,
                                 Double temperature) {
}
