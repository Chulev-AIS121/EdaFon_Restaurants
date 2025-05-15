package com.example.EdaFon_Restaurants.service;

import com.example.EdaFon_Restaurants.entity.Dish;
import com.example.EdaFon_Restaurants.entity.Restaurant;
import com.example.EdaFon_Restaurants.repository.DishRepository;
import com.example.EdaFon_Restaurants.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> findRestaurantsByDishNames(List<String> dishNames) {
        Set<String> cuisines = dishRepository.findByNameInIgnoreCase(dishNames).stream()
                .map(Dish::getCuisineType)
                .collect(Collectors.toSet());

        return restaurantRepository.findByCuisineTypeIn(cuisines);
    }

    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant update(Long id, Restaurant updated) {
        Restaurant existing = restaurantRepository.findById(id).orElseThrow();
        existing.setName(updated.getName());
        existing.setAddress(updated.getAddress());
        existing.setCuisineType(updated.getCuisineType());
        existing.setTypeOfRestaurant(updated.getTypeOfRestaurant());
        existing.setYandexMaps(updated.getYandexMaps());
        return restaurantRepository.save(existing);
    }

    public void delete(Long id) {
        restaurantRepository.deleteById(id);
    }
}

