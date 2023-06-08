package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.general.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "forecast_data")
public class ForecastData extends BaseEntity {

    // Class representing the database model for weather forecast data

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "forecast_time")
    private LocalDateTime forecastTime;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "USER_ID")
    private Long userId;
}
