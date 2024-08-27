package com.example.Project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Project.tables.HouseBooking;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface HouseBookingRepository extends JpaRepository<HouseBooking, Long> {
    @Query("SELECT hb FROM HouseBooking hb JOIN hb.customer c WHERE c.customer_id = ?1")
    List<HouseBooking> houseBookingByCustomer(long customerId);

    @Query(value = "SELECT * FROM house_booking WHERE booking_status = 1",nativeQuery = true)
    List<HouseBooking> countAllHouseBooking();

    @Query(value = "SELECT COUNT(*) as number_of_booking,MONTH(start_date) as months FROM house_booking WHERE booking_status = 1 " +
            "AND YEAR(start_date) = YEAR(NOW()) GROUP BY MONTH(start_date)",nativeQuery = true)
    List<Map<String,Object>> bookingReportDashboard();

    @Query(value = "SELECT * FROM house_booking WHERE start_date BETWEEN :startDate AND :endDate AND end_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<HouseBooking> reportByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}

