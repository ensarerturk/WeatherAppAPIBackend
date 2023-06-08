package org.example.repository;

import org.example.model.ForecastData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForecastDataRepository extends JpaRepository<ForecastData, Long> {

    /**
     * Retrieves forecast data based on the given city ID.
     *
     * @param cityId City ID
     * @return Forecast data list
     */
    List<ForecastData> findByCityId(Long cityId);
}
