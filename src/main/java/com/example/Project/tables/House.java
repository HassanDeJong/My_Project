package com.example.Project.tables;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "house")
@Data
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long house_id;

    @Column(
            unique = true,
            nullable = false
    )
    private String houseNumber;
    private String title;
    private String description;
    private String type;
    private double size;
    private int bedrooms;
    private int bathrooms;
    private String address;
    private double latitude;
    private double longitude;
    private String status;
    private String color;
    private String otherDetails;
    private double buyingPrice;
    private double rentingPrice;
}

