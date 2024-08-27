package com.example.Project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Project.tables.House;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
    
}
