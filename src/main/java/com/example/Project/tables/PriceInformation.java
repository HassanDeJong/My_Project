package com.example.Project.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class PriceInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priceId;
    private double renting_price;
    private double buying_price;
    @OneToOne
    @JoinColumn(name = "house_id",referencedColumnName = "house_id")
    private House house;
}
