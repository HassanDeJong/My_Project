package com.example.Project.controllers;

import com.example.Project.tables.HouseBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Project.services.HouseBuyingService;
import com.example.Project.tables.HouseBuying;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/house-buyings")
@CrossOrigin
public class HouseBuyingController {
    @Autowired
    public HouseBuyingService houseBuyingService;

    @GetMapping
    public ResponseEntity<List<HouseBuying>> getAllHouseBuying() {
        List<HouseBuying> houseBuyings = houseBuyingService.getAllHouseBuyings();
        return new ResponseEntity<>(houseBuyings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HouseBuying> getHouseBuyingById(@PathVariable Long id) {
        Optional<HouseBuying> houseBuying = houseBuyingService.getHouseBuyingById(id);
        return houseBuying.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<HouseBuying> createHouseBuying(@RequestBody HouseBuying houseBuying) {
        HouseBuying savedHouseBuying = houseBuyingService.saveHouseBuying(houseBuying);
        return new ResponseEntity<>(savedHouseBuying, HttpStatus.CREATED);
    }


    @PostMapping("/customer/{customerId}/house/{houseId}")
    public ResponseEntity<HouseBuying> makeHouseBooking(@RequestBody HouseBuying houseBuying, @PathVariable long customerId, @PathVariable long houseId) {
        HouseBuying makeHouseBuying = houseBuyingService.makeHouseBuying(houseBuying, customerId, houseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(makeHouseBuying);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHouseBuying(@PathVariable Long id) {
        houseBuyingService.deleteHouseBuying(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/by-customer-id/{id}")
    public ResponseEntity<List<HouseBuying>> houseBuyingByCustomerId(@PathVariable long id) {
        List<HouseBuying> houseBuying = houseBuyingService.houseBuyingByCustomerId(id);
        return new ResponseEntity<>(houseBuying, HttpStatus.OK);
    }

    @GetMapping("/report-dashboard-buying")
    public ResponseEntity<List<Map<String,Object>>> reportDashBoardBuying() {
        List<Map<String,Object>> houseBuying = houseBuyingService.reportDashBoardBuying();
        return new ResponseEntity<>(houseBuying, HttpStatus.OK);
    }

    @PutMapping("/update-buying/{id}")
    public ResponseEntity<HouseBuying> updateHouseBuying(@PathVariable long id, @RequestBody HouseBuying houseBuying) {
        HouseBuying updatedHouseBuying = houseBuyingService.updateHouseBuying(id, houseBuying);
        if (updatedHouseBuying != null) {
            return ResponseEntity.ok(updatedHouseBuying);
        }
        return ResponseEntity.notFound().build();
    }
}

