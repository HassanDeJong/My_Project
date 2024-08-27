package com.example.Project.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Project.tables.HouseLocation;

import java.util.Optional;

@Repository
public interface HouseLocationRepository extends JpaRepository<HouseLocation,Long> {

    @Query(value = "SELECT * FROM house_location WHERE house_id = ?1", nativeQuery = true)
    Optional<HouseLocation> houseLocationByHouseId(long house_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM house_location WHERE house_id = ?1", nativeQuery = true)
    void deleteLocationByHouseId(long id);
}
