package com.example.Project.controllers;

import com.example.Project.dtos.PriceInformationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Project.services.PriceInformationService;
import com.example.Project.tables.PriceInformation;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/price-information")
@CrossOrigin
public class PriceInformationController {

    @Autowired
    public PriceInformationService priceInformationService;

    @GetMapping("/allPrice")
    public ResponseEntity<List<PriceInformation>> getAllPriceInformation() {
        List<PriceInformation> priceInformationList = priceInformationService.getAllPriceInformation();
        return ResponseEntity.ok().body(priceInformationList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceInformation> getPriceInformationById(@PathVariable Long id) {
        Optional<PriceInformation> priceInformation = priceInformationService.getPriceInformationById(id);
        return priceInformation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addPrice")
    public ResponseEntity<PriceInformation> createPriceInformation(@RequestBody PriceInformation priceInformation) {
        PriceInformation createdPriceInformation = priceInformationService.savePriceInformation(priceInformation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPriceInformation);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Optional<PriceInformation>> updatePriceInformation(@PathVariable long id, @RequestBody PriceInformationDTO priceInformation) {
        Optional<PriceInformation> updatePriceInformation = priceInformationService.updatePriceInformation(id,priceInformation);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatePriceInformation);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePriceInformation(@PathVariable Long id) {
        priceInformationService.deletePriceInformation(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-by-house-id/{id}")
    public ResponseEntity<Void> deleteHouseByHouseId(@PathVariable Long id) {
        priceInformationService.deleteHouseByHouseId(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/by-house-id/{id}")
    public ResponseEntity<PriceInformation> getPriceInformationByHouseId(@PathVariable Long id) {
        Optional<PriceInformation> priceInformation = priceInformationService.getPriceInformationByHouseId(id);
        return priceInformation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}

