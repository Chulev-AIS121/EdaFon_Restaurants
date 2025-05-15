package com.example.EdaFon_Restaurants.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {
    private String name;
    private String address;
    private String cuisineType;
    private String typeOfRestaurant;
    private String yandexMaps;
}