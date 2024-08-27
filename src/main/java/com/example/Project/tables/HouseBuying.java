package com.example.Project.tables;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class HouseBuying {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long house_buying_id;
    @ManyToOne
    @JoinColumn(name = "house_id", referencedColumnName="house_id")
    private House house;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName="customer_id")
    private Customer customer;
    private double buyingPrice;
    private LocalDate buyingDate;
    private int byingConfirmation;
}
