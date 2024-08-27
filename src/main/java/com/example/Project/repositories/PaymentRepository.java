package com.example.Project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Project.tables.Payments;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Long> {
    @Query(value = "SELECT * FROM payments WHERE house_buying_id  != ''",nativeQuery = true)
    List<Payments> getAllPaymentsHouseBuying();

    @Query(value = "SELECT * FROM payments WHERE house_booking_id != ''",nativeQuery = true)
    List<Payments> getAllPaymentsHouseRenting();
}

