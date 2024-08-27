package com.example.Project.dtos;

import lombok.Data;

@Data
public class LocationDTO {
    private String region_name;
    private String country_name;
    private String shehia_name;
    private String other_location_details;
    private long house_id;
}
