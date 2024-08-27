package com.example.Project.dtos;

import lombok.Data;

@Data
public class PriceInformationDTO {
    private double renting_price;
    private double buying_price;
    private long house_id;
}
