package com.example.EdaFon_Restaurants.init;

import com.example.EdaFon_Restaurants.entity.Dish;
import com.example.EdaFon_Restaurants.entity.Restaurant;
import com.example.EdaFon_Restaurants.repository.DishRepository;
import com.example.EdaFon_Restaurants.repository.RestaurantRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    @PostConstruct
    public void init() throws Exception {
        loadDishes();
        loadRestaurants();
    }

    private void loadDishes() {
        if (dishRepository.count() > 0) {
            log.info("Блюда уже загружены.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new ClassPathResource("db/changelog/data/dish.csv").getInputStream(), StandardCharsets.UTF_8))) {
            reader.readLine(); // skip header

            int success = 0;
            int failed = 0;

            for (String line : reader.lines().toList()) {
                try {
                    String[] tokens = line.split(",", -1); // preserve empty fields
                    if (tokens.length != 4) {
                        throw new IllegalArgumentException("Ожидалось 4 поля, получено " + tokens.length);
                    }
                    Dish dish = new Dish(
                            UUID.fromString(tokens[0].trim()),
                            tokens[1].trim(),
                            tokens[2].trim(),
                            tokens[3].trim()
                    );
                    dishRepository.save(dish);
                    success++;
                } catch (Exception e) {
                    failed++;
                    log.warn("Ошибка при загрузке блюда: [{}] — {}", line, e.getMessage());
                }
            }

            log.info("Загрузка блюд завершена. Успешно: {}, Ошибок: {}", success, failed);
        } catch (Exception e) {
            log.error("Ошибка чтения файла dish.csv: {}", e.getMessage(), e);
        }
    }

    private void loadRestaurants() {
        if (restaurantRepository.count() > 0) {
            log.info("Рестораны уже загружены.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new ClassPathResource("db/changelog/data/restaurants.csv").getInputStream(), StandardCharsets.UTF_8))) {
            reader.readLine(); // skip header

            int success = 0;
            int failed = 0;

            for (String line : reader.lines().toList()) {
                try {
                    String[] tokens = line.split(",", -1);
                    if (tokens.length != 5) {
                        throw new IllegalArgumentException("Ожидалось 5 полей, получено " + tokens.length);
                    }
                    Restaurant restaurant = new Restaurant(
                            null,
                            tokens[0].trim(),
                            tokens[1].trim(),
                            tokens[2].trim(),
                            tokens[3].trim(),
                            tokens[4].trim()
                    );
                    restaurantRepository.save(restaurant);
                    success++;
                } catch (Exception e) {
                    failed++;
                    log.warn("Ошибка при загрузке ресторана: [{}] — {}", line, e.getMessage());
                }
            }

            log.info("Загрузка ресторанов завершена. Успешно: {}, Ошибок: {}", success, failed);
        } catch (Exception e) {
            log.error("Ошибка чтения файла restaurants.csv: {}", e.getMessage(), e);
        }
    }
}
