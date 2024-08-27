package com.example.Project.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payment_id;
    private String controllNumber;
    @ManyToOne
    @JoinColumn(name = "house_buying_id", referencedColumnName = "house_buying_id", nullable = true)
    private HouseBuying houseBuying;
    @ManyToOne
    @JoinColumn(name = "house_booking_id", referencedColumnName = "house_booking_id", nullable = true)
    private HouseBooking houseBooking;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer_data;
    private int payment_status;
}
