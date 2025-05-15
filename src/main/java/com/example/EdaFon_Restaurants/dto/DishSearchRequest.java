package com.example.EdaFon_Restaurants.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class DishSearchRequest {
    @NotEmpty
    private List<String> dishNames;
}
