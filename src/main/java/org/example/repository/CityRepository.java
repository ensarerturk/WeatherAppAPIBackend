package org.example.repository;

import org.example.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    /**
     * Returns a City object based on the given city name.
     *
     * @param cityName City name
     * @return City object
     */
    City findByName(String cityName);

    /**
     * Returns the city list according to the given user ID.
     *
     * @param userId User ID
     * @return Şehir object
     */
    List<City> findByUserId(Long userId);
}
