package com.example.EdaFon_Restaurants.repository;

import com.example.EdaFon_Restaurants.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DishRepository extends JpaRepository<Dish, UUID> {
    List<Dish> findByNameInIgnoreCase(List<String> names);
}
