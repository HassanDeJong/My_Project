package com.example.Project.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class HouseLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long location_id;
    private String region_name;
    private String country_name;
    private String shehia_name;
    private String other_location_details;
    @ManyToOne
    @JoinColumn(name = "house_id", referencedColumnName = "house_id")
    private House house_id;
}
