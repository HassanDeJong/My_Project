package com.example.Project.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class HouseBooking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long house_booking_id;  
    @ManyToOne
    @JoinColumn(name = "house_id", referencedColumnName="house_id")
    private House house;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName="customer_id")
    private Customer customer;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private double totalPrice;
    private int bookingStatus;
}

