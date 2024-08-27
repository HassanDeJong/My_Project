package com.example.Project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Project.tables.HouseBuying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface HouseBuyingRepository extends JpaRepository<HouseBuying, Long> {

    @Query(value = "SELECT hb FROM HouseBuying hb JOIN hb.customer c WHERE c.customer_id = ?1")
    List<HouseBuying> houseBuyingByCustomerId(long customerId);

    @Query(value = "SELECT * FROM house_buying where bying_confirmation = 1",nativeQuery = true)
    List<HouseBuying> countAllHouseBuying();

    @Query(value = "SELECT COUNT(*) AS number_of_buying,MONTH(buying_date) as months FROM house_buying " +
            "WHERE bying_confirmation = 1 and YEAR(buying_date) = YEAR(NOW()) GROUP BY MONTH(buying_date)",nativeQuery = true)
    List<Map<String,Object>> reportDashBoardBuying();

    @Query(value = "SELECT * from house_buying WHERE DATE(buying_date) = ?1",nativeQuery = true)
    List<HouseBuying> reportBuyingsByDate(Date buyingDate);
}

