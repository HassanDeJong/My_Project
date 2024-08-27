package com.example.Project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Project.services.HouseService;
import com.example.Project.tables.House;

@RestController
@RequestMapping("/api/houses")
@CrossOrigin
public class HouseController {
    @Autowired
    public HouseService houseService;

    @GetMapping
    public List<House> getAllHouses() {
        return houseService.getAllHouses();
    }

    @GetMapping("/{id}")
    public House getHouseById(@PathVariable Long id) {
        return houseService.getHouseById(id);
    }

    @PostMapping
    public House createHouse(@RequestBody House house) {
        return houseService.saveHouse(house);
    }

    @PutMapping("/update/{id}")
    public House updateHouse(@PathVariable long id,@RequestBody House house){
        return houseService.updateHouse(id, house);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHouse(@PathVariable Long id) {
        houseService.deleteHouse(id);
        return ResponseEntity.ok().build();
    }
}

