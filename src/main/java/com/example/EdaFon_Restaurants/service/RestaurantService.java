package com.example.EdaFon_Restaurants.service;

import com.example.EdaFon_Restaurants.entity.Dish;
import com.example.EdaFon_Restaurants.entity.Restaurant;
import com.example.EdaFon_Restaurants.repository.DishRepository;
import com.example.EdaFon_Restaurants.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantService {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> findRestaurantsByDishNames(List<String> dishNames) {
        // Преобразуем dishNames в нижний регистр
        List<String> lowercasedNames = dishNames.stream()
                .map(String::toLowerCase)
                .toList();

        // Получаем блюда с совпадающими именами (игнорируя регистр)
        List<Dish> dishes = dishRepository.findByNameInIgnoreCase(lowercasedNames);
        log.info("Найдено блюд: {}", dishes.size());
        dishes.forEach(d -> log.info("Блюдо: {}, кухня: {}", d.getName(), d.getCuisineType()));

        // Извлекаем уникальные типы кухонь и приводим к нижнему регистру
        Set<String> cuisines = dishes.stream()
                .map(Dish::getCuisineType)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
        log.info("Типы кухонь для поиска ресторанов: {}", cuisines);

        // Ищем рестораны по найденным типам кухонь (без учёта регистра)
        List<Restaurant> restaurants = restaurantRepository.findByCuisineTypeInIgnoreCase(cuisines);
        log.info("Найдено ресторанов: {}", restaurants.size());

        return restaurants;
    }

    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant update(Long id, Restaurant updated) {
        Restaurant existing = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
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
