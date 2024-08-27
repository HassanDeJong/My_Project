package com.example.Project.controllers;


import com.example.Project.dtos.ReportBuyingDateDTO;
import com.example.Project.dtos.ReportDateDTO;
import com.example.Project.repositories.ReportRepository;
import com.example.Project.tables.HouseBooking;
import com.example.Project.tables.HouseBuying;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    public ReportRepository reportRepository;

    @GetMapping("/dashboard-report-1")
    public ResponseEntity<Map<String,Long>> getDashboardReport(){
     Map<String,Long> summary = new HashMap<>();
     summary.put("customers",reportRepository.countAllCustomers());
     summary.put("users",reportRepository.countAllUsers());
     summary.put("houseBooking",reportRepository.countAllHouseBooking());
     summary.put("houseBuying",reportRepository.countAllHouseBuying());
     summary.put("allHouses",reportRepository.getAllHouses());
     return ResponseEntity.ok(summary);
    }

    @PostMapping("/report-by-date")
    public List<HouseBooking> reportByDate(@RequestBody ReportDateDTO reportDateDTO){
        return reportRepository.reportDate(reportDateDTO);
    }

    @PostMapping("/report-buying-by-date")
    public List<HouseBuying> reportBuyingsByDate(@RequestBody ReportBuyingDateDTO reportBuyingDateDTO){
        return reportRepository.reportBuyingsByDate(reportBuyingDateDTO.getBuyingDate());
    }
}
