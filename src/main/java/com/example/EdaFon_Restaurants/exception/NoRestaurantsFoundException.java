package com.example.EdaFon_Restaurants.exception;

public class NoRestaurantsFoundException extends RuntimeException {
    public NoRestaurantsFoundException(String message) {
        super(message);
    }
}
