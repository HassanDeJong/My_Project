package com.example.Project.services;

import java.util.List;
import java.util.Optional;

import com.example.Project.dtos.LocationDTO;
import com.example.Project.repositories.HouseRepository;
import com.example.Project.tables.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Project.repositories.HouseLocationRepository;
import com.example.Project.tables.HouseLocation;

import jakarta.transaction.Transactional;

@Service
public class HouseLocationService {

    @Autowired
    public HouseLocationRepository houseLocationRepository;
    @Autowired
    public HouseRepository houseRepository;


    public HouseLocation SaveHouseLocation(HouseLocation houseLocation){
        return houseLocationRepository.save(houseLocation);
    }

    @Transactional
    public Optional<HouseLocation> updateHouseLocation(long id,LocationDTO houseLocation){
        Optional<House> house =  houseRepository.findById(houseLocation.getHouse_id());
        return houseLocationRepository.findById(id).map(h->{
            h.setCountry_name(houseLocation.getCountry_name());
            h.setHouse_id(house.get());
            h.setRegion_name(houseLocation.getRegion_name());
            h.setShehia_name(houseLocation.getShehia_name());
            h.setOther_location_details(houseLocation.getOther_location_details());
            return h;
        });
    }

    public List<HouseLocation> getAllHouseLocation(){
        return houseLocationRepository.findAll();
    }

    public Optional<HouseLocation> getHouseLocationById(Long id){
       Optional<HouseLocation> h = houseLocationRepository.findById(id);
       if(h.isPresent()){
        return h;
       }else{
        return Optional.empty();
       }
    }

    public void deleteHouseLocation(Long id) {
        houseLocationRepository.deleteById(id);
    }

    public void deleteLocationByHouseId(Long id) {
        houseLocationRepository.deleteLocationByHouseId(id);
    }

    public Optional<HouseLocation> houseLocationByHouseId(Long id){
        Optional<HouseLocation> h = houseLocationRepository.houseLocationByHouseId(id);
        if(h.isPresent()){
            return h;
        }else{
            return Optional.empty();
        }
    }

}
