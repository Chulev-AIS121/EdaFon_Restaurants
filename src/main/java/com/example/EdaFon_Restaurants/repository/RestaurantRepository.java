package com.example.EdaFon_Restaurants.repository;

import com.example.EdaFon_Restaurants.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByCuisineTypeIn(Set<String> cuisines);
}
