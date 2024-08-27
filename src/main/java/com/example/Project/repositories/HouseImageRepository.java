package com.example.Project.repositories;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Project.tables.HouseImage;

@Repository
public interface HouseImageRepository extends JpaRepository<HouseImage, Long> {
    // List<HouseImage> findByHouseId(Long houseId);
    @Query(value = "SELECT * FROM house_image WHERE house_id = ?1",nativeQuery = true)
    List<HouseImage> findByHouseId(Long houseId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM house_image where house_id = ?1",nativeQuery = true)
    void deleteHouseImageByHouseId(long houseId);
}

