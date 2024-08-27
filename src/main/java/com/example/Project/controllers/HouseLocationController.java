package com.example.Project.controllers;

import java.util.List;
import java.util.Optional;

import com.example.Project.dtos.LocationDTO;
import com.example.Project.tables.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project.services.HouseLocationService;
import com.example.Project.tables.HouseLocation;

@RestController
@RequestMapping("/api/house-location")
@CrossOrigin
public class HouseLocationController {

    @Autowired
    public HouseLocationService houseLocationService;

    @PostMapping("/save")
    public HouseLocation savHouseLocation(@RequestBody HouseLocation houseLocation){
        return houseLocationService.SaveHouseLocation(houseLocation);
    }

    @PutMapping("/update/{id}")
    public Optional<HouseLocation> updateHouseLocation(@PathVariable("id") Long id,@RequestBody LocationDTO houseLocation){
        return houseLocationService.updateHouseLocation(id,houseLocation);
    }

    @GetMapping("/all")
    public List<HouseLocation> getAllHouseLocation(){
        return houseLocationService.getAllHouseLocation();
    }

    @GetMapping("/{id}")
    public Optional<HouseLocation> getHouseLocationById(@PathVariable("id") Long id){
        return houseLocationService.getHouseLocationById(id);
    }
    @GetMapping("/by-house-id/{id}")
    public Optional<HouseLocation> houseLocationByHouseId(@PathVariable("id") Long id){
        return houseLocationService.houseLocationByHouseId(id);
    }


    @DeleteMapping("/{id}")
    public HttpStatus deleteHouseLocationById(@PathVariable("id") Long id){
        houseLocationService.deleteHouseLocation(id);
        return HttpStatus.OK;
    }

    @DeleteMapping("/delete-by-house-id/{id}")
    public HttpStatus deleteLocationByHouseId(@PathVariable("id") Long id){
        houseLocationService.deleteLocationByHouseId(id);
        return HttpStatus.OK;
    }
}
