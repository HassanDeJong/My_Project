package com.example.Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Project.services.HouseBookingService;
import com.example.Project.tables.HouseBooking;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/house-bookings")
@CrossOrigin
public class HouseBookingController {

    @Autowired
    public HouseBookingService houseBookingService;

    @GetMapping
    public ResponseEntity<List<HouseBooking>> getAllHouseBookings() {
        List<HouseBooking> houseBookingList = houseBookingService.getAllHouseBookings();
        return ResponseEntity.ok().body(houseBookingList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HouseBooking> getHouseBookingById(@PathVariable Long id) {
        Optional<HouseBooking> houseBooking = houseBookingService.getHouseBookingById(id);
        return houseBooking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HouseBooking> createHouseBooking(@RequestBody HouseBooking houseBooking) {
        HouseBooking createdHouseBooking = houseBookingService.saveHouseBooking(houseBooking);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHouseBooking);
    }


    @PostMapping("/customer/{customerId}/house/{houseId}")
    public ResponseEntity<HouseBooking> makeHouseBooking(@RequestBody HouseBooking houseBooking, @PathVariable long customerId, @PathVariable long houseId) {
        HouseBooking createdHouseBooking = houseBookingService.makeHouseBooking(houseBooking, customerId, houseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHouseBooking);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHouseBooking(@PathVariable Long id) {
        houseBookingService.deleteHouseBooking(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-customer-id/{id}")
    public ResponseEntity<List<HouseBooking>> houseBookingByCustomer(@PathVariable long id) {
        List<HouseBooking> houseBookingList = houseBookingService.houseBookingByCustomer(id);
        return ResponseEntity.ok().body(houseBookingList);
    }

    @GetMapping("/booking-report-dashboard")
    public ResponseEntity<List<Map<String,Object>>> bookingReportDashboard() {
        List<Map<String,Object>> houseBookingList = houseBookingService.bookingReportDashboard();
        return ResponseEntity.ok().body(houseBookingList);
    }

    @PutMapping("/update-booking/{id}")
    public ResponseEntity<HouseBooking> updateHouseBooking(@PathVariable long id, @RequestBody HouseBooking houseBooking) {
        HouseBooking updatedHouseBooking = houseBookingService.updateHouseBooking(id, houseBooking);
        if (updatedHouseBooking != null) {
            return ResponseEntity.ok(updatedHouseBooking);
        }
        return ResponseEntity.notFound().build();
    }
}
