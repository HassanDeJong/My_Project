package com.example.Project.repositories;

import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Project.tables.PriceInformation;

@Repository
public interface PriceInformationRepository extends JpaRepository<PriceInformation, Long> {
    @Query(value="SELECT * FROM price_information where house_id = ?1",nativeQuery = true)
    Optional<PriceInformation> getPriceInformationByHouseId(Long houseId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM price_information WHERE house_id = ?1",nativeQuery = true)
    void deleteHouseByHouseId(long id);
}

