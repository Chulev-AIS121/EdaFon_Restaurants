package com.example.EdaFon_Restaurants.repository;

import com.example.EdaFon_Restaurants.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurant r WHERE LOWER(r.cuisineType) IN :cuisines")
    List<Restaurant> findByCuisineTypeInIgnoreCase(@Param("cuisines") Set<String> lowerCuisines);

}
