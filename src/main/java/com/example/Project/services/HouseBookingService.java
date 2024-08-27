package com.example.Project.services;

import com.example.Project.tables.Customer;
import com.example.Project.tables.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Project.repositories.HouseBookingRepository;
import com.example.Project.repositories.PriceInformationRepository;
import com.example.Project.tables.HouseBooking;
import com.example.Project.tables.PriceInformation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HouseBookingService {

    @Autowired
    public HouseBookingRepository houseBookingRepository;
    @Autowired
    public PriceInformationRepository priceInformationRepository;

    @Autowired
    public HouseService houseService;

    @Autowired
    public CustomerService customerService;



    public List<HouseBooking> getAllHouseBookings() {
        return houseBookingRepository.findAll();
    }

    public Optional<HouseBooking> getHouseBookingById(Long id) {
        return houseBookingRepository.findById(id);
    }

    public HouseBooking saveHouseBooking(HouseBooking houseBooking) {
        Optional<PriceInformation> price = priceInformationRepository.getPriceInformationByHouseId(houseBooking.getHouse().getHouse_id());
        if (price.isPresent()) {
            LocalDate startDate = houseBooking.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = houseBooking.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            long numberOfMonths = ChronoUnit.MONTHS.between(startDate.withDayOfMonth(1), endDate.withDayOfMonth(1));

            double totalPrice = price.get().getRenting_price() * numberOfMonths;
            houseBooking.setTotalPrice(totalPrice);
            houseBooking.getHouse().setStatus("1");
            return houseBookingRepository.save(houseBooking);
        } else {
            return new HouseBooking();
        }

    }


    public HouseBooking makeHouseBooking(HouseBooking houseBooking) {
        Optional<PriceInformation> price = priceInformationRepository.getPriceInformationByHouseId(houseBooking.getHouse().getHouse_id());
        if (price.isPresent()) {
            LocalDate startDate = houseBooking.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = houseBooking.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            long numberOfMonths = ChronoUnit.MONTHS.between(startDate.withDayOfMonth(1), endDate.withDayOfMonth(1));

            double totalPrice = price.get().getRenting_price() * numberOfMonths;
            houseBooking.setTotalPrice(totalPrice);
            houseBooking.getHouse().setStatus("1");
            return houseBookingRepository.save(houseBooking);
        } else {
            return new HouseBooking();
        }

    }


    public HouseBooking makeHouseBooking(HouseBooking houseBooking, long customerId, long houseId) {
        Customer customer = customerService.findCustomerById(customerId);
        House house = houseService.getHouseById(houseId);
        Optional<PriceInformation> price = priceInformationRepository.getPriceInformationByHouseId(house.getHouse_id());
        if (price.isPresent()) {
            LocalDate startDate = houseBooking.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = houseBooking.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            long numberOfMonths = ChronoUnit.MONTHS.between(startDate.withDayOfMonth(1), endDate.withDayOfMonth(1));

            double totalPrice = price.get().getRenting_price() * numberOfMonths;
            houseBooking.setTotalPrice(totalPrice);
            houseBooking.setCustomer(customer);
            houseBooking.setHouse(house);
            house.setStatus("1");
            houseService.saveRentedHouse(house);
            return houseBookingRepository.save(houseBooking);
        } else {
            return new HouseBooking();
        }

    }

    public void deleteHouseBooking(Long id) {
        houseBookingRepository.deleteById(id);
    }

    public List<HouseBooking> houseBookingByCustomer(long customerId) {
        return houseBookingRepository.houseBookingByCustomer(customerId);
    }

    public List<Map<String, Object>> bookingReportDashboard() {
        return houseBookingRepository.bookingReportDashboard();
    }

    public HouseBooking updateHouseBooking(long id, HouseBooking houseBooking) {
        if (houseBookingRepository.existsById(id)) {
            houseBooking.setHouse_booking_id(id);
            return houseBookingRepository.save(houseBooking);
        }
        return null;
    }
}

