package com.example.EdaFon_Restaurants.exception;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(String name) {
        super("Блюдо не найдено: " + name);
    }
}
