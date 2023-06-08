package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.general.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "CityWeather")
public class WeatherData extends BaseEntity {

    // Represents weather data for a city.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_id", nullable = false)
    private Long cityId;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @Column(name = "temperature", nullable = false)
    private Double temperature;

    @Column(name = "weather_time")
    private LocalDateTime weatherTime;

    @Column(name = "USER_ID")
    private Long userId;

}
