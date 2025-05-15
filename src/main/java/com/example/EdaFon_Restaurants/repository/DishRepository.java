package com.example.EdaFon_Restaurants.repository;

import com.example.EdaFon_Restaurants.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface DishRepository extends JpaRepository<Dish, UUID> {
    @Query("SELECT d FROM Dish d WHERE LOWER(d.name) IN :names")
    List<Dish> findByNameInIgnoreCase(@Param("names") List<String> lowerNames);

}
