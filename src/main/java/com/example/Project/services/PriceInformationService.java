package com.example.Project.services;

import com.example.Project.dtos.PriceInformationDTO;
import com.example.Project.repositories.HouseRepository;
import com.example.Project.tables.House;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Project.repositories.PriceInformationRepository;
import com.example.Project.tables.PriceInformation;

import java.util.List;
import java.util.Optional;

@Service
public class PriceInformationService {

    @Autowired
    public PriceInformationRepository priceInformationRepository;
    @Autowired
    public HouseRepository houseRepository;


    public List<PriceInformation> getAllPriceInformation() {
        return priceInformationRepository.findAll();
    }

    public Optional<PriceInformation> getPriceInformationById(Long id) {
        return priceInformationRepository.findById(id);
    }

    public PriceInformation savePriceInformation(PriceInformation priceInformation) {
        return priceInformationRepository.save(priceInformation);
    }

//    public PriceInformation updatePriceInformation(long id,PriceInformation priceInformation) {
//        if(priceInformationRepository.existsById(id)){
//            priceInformation.setPriceId(id);
//        }
//        return priceInformationRepository.save(priceInformation);
//    }

    public void deletePriceInformation(Long id) {
        priceInformationRepository.deleteById(id);
    }

    public void deleteHouseByHouseId(long id){
        priceInformationRepository.deleteHouseByHouseId(id);
    }

    public Optional<PriceInformation> getPriceInformationByHouseId(Long id) {
        return priceInformationRepository.getPriceInformationByHouseId(id);
    }

    @Transactional
    public Optional<PriceInformation> updatePriceInformation(long id, PriceInformationDTO priceInformationDTO){
        Optional<House> house = houseRepository.findById(priceInformationDTO.getHouse_id());
        return priceInformationRepository.findById(id).map(p->{
            p.setBuying_price(priceInformationDTO.getBuying_price());
            p.setRenting_price(priceInformationDTO.getRenting_price());
            p.setHouse(house.get());
            return p;
        });
    }
}

