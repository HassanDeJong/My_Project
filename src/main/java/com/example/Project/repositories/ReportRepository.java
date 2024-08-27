package com.example.Project.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.Project.dtos.ReportDateDTO;
import com.example.Project.tables.HouseBooking;
import com.example.Project.tables.HouseBuying;

@Repository
public class ReportRepository {

    @Autowired
    public HouseRepository houseRepository;

    @Autowired
    public HouseBookingRepository houseBookingRepository;

    @Autowired
    public HouseBuyingRepository houseBuyingRepository;

    @Autowired
    public LoginRepository loginRepository;

    @Autowired
    public CustomerRepository customerRepository;


    public Long getAllHouses(){
        return houseRepository.count();
    }

    public Long countAllCustomers(){
        return customerRepository.count();
    }

    public Long countAllUsers(){
        return loginRepository.count();
    }

    public Long countAllHouseBuying(){
        return (long) houseBuyingRepository.countAllHouseBuying().size();
    }

    public Long countAllHouseBooking(){
        return (long) houseBookingRepository.countAllHouseBooking().size();
    }

    public List<HouseBooking> reportDate(ReportDateDTO reportDateDTO){
        return houseBookingRepository.reportByDate(reportDateDTO.getStartDate(),reportDateDTO.getEndDate());
    }

    public List<HouseBuying> reportBuyingsByDate(Date buyingDate){
        return houseBuyingRepository.reportBuyingsByDate(buyingDate);
    }
}
