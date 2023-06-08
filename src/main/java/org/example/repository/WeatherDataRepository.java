package org.example.repository;

import org.example.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {

    /**
     * Finds weather data by city ID.
     *
     * @param cityId City ID
     * @return Weather data list
     */
    List<WeatherData> findByCityId(Long cityId);
}
