package com.example.EdaFon_Restaurants.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dish", schema = "public")
public class Dish {
    @Id
    private UUID id;
    private String name;
    private String description;
    private String cuisineType;
}
