package com.example.Project.services;

import com.example.Project.tables.Customer;
import com.example.Project.tables.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Project.repositories.HouseBuyingRepository;
import com.example.Project.repositories.PriceInformationRepository;
import com.example.Project.tables.HouseBuying;
import com.example.Project.tables.PriceInformation;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HouseBuyingService {
    @Autowired
    public HouseBuyingRepository houseBuyingRepository;
    @Autowired
    private PriceInformationRepository priceInformationRepository;

    @Autowired
    public HouseService houseService;

    @Autowired
    public CustomerService customerService;

    public List<HouseBuying> getAllHouseBuyings() {
        return houseBuyingRepository.findAll();
    }

    public Optional<HouseBuying> getHouseBuyingById(Long id) {
        return houseBuyingRepository.findById(id);
    }


    public HouseBuying saveHouseBuying(HouseBuying houseBuying) {
        Optional<PriceInformation> price = priceInformationRepository.getPriceInformationByHouseId(houseBuying.getHouse().getHouse_id());
        if (price.isPresent()) {
            double totalPrice = price.get().getBuying_price();
            houseBuying.setBuyingPrice(totalPrice);
            houseBuying.getHouse().setStatus("1");
            return houseBuyingRepository.save(houseBuying);
        } else {
            return new HouseBuying();
        }
    }


    public HouseBuying makeHouseBuying(HouseBuying houseBuying, long customerId, long houseId) {
        Customer customer = customerService.findCustomerById(customerId);
        House house = houseService.getHouseById(houseId);
        Optional<PriceInformation> price = priceInformationRepository.getPriceInformationByHouseId(house.getHouse_id());
        if (price.isPresent()) {
            double totalPrice = price.get().getBuying_price();
            houseBuying.setBuyingPrice(totalPrice);
            houseBuying.setCustomer(customer);
            houseBuying.setHouse(house);
            house.setStatus("1");
            house.setBuyingPrice(price.get().getBuying_price());
            house.setRentingPrice(price.get().getRenting_price());
            houseService.saveRentedHouse(house);
            return houseBuyingRepository.save(houseBuying);
        } else {
            return new HouseBuying();
        }
    }


    public void deleteHouseBuying(Long id) {
        houseBuyingRepository.deleteById(id);
    }


    public List<HouseBuying> houseBuyingByCustomerId(long customerId) {
        return houseBuyingRepository.houseBuyingByCustomerId(customerId);
    }


    public List<Map<String, Object>> reportDashBoardBuying() {
        return houseBuyingRepository.reportDashBoardBuying();
    }


    public HouseBuying updateHouseBuying(long id, HouseBuying houseBuying) {
        if (houseBuyingRepository.existsById(id)) {
            houseBuying.setHouse_buying_id(id);
            return houseBuyingRepository.save(houseBuying);
        }
        return null;
    }
}
