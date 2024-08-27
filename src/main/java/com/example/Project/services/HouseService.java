package com.example.Project.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Project.repositories.HouseRepository;
import com.example.Project.tables.House;

@Service
public class HouseService {
    @Autowired
    public HouseRepository houseRepository;
    
    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }
    
    public House getHouseById(Long id) {
        return houseRepository.findById(id).orElseThrow(() -> new NoSuchElementException("House not found with id: " + id));
    }

    public House updateHouse(long id,House house){
        if(houseRepository.existsById(id)){
           house.setHouse_id(id);
        }
        return houseRepository.save(house);
    }
    
    public House saveHouse(House house) {
        house.setStatus("0");
        return houseRepository.save(house);
    }
    
    public void deleteHouse(Long id) {
        houseRepository.deleteById(id);
    }

    public void saveRentedHouse(House house) {
        houseRepository.save(house);
    }
}

