package com.example.Project.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.Project.Utitlity.ImageUtility;
import com.example.Project.repositories.HouseImageRepository;
import com.example.Project.repositories.HouseRepository;
import com.example.Project.tables.House;
import com.example.Project.tables.HouseImage;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/house-images")
@CrossOrigin
public class HouseImageController {
    @Autowired
    public HouseImageRepository houseImageRepository;

    @Autowired
    public HouseRepository houseRepository;

    @PostMapping(value = "/upload/{house_id}",consumes = "multipart/form-data")
    @Transactional
    public ResponseEntity.BodyBuilder uploadImage(@RequestParam("imageFile") MultipartFile file, @PathVariable("house_id") Long house_id) throws IOException {
        HouseImage img = new HouseImage();
        img.setImageName(file.getOriginalFilename());
        img.setImageUrl(ImageUtility.compressBytes(file.getBytes()));
        Optional<House> h = houseRepository.findById(house_id);
        if (h.isPresent()) {
            img.setHouse(h.get());
            houseImageRepository.save(img);
            return ResponseEntity.status(HttpStatus.OK); 
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-id/{id}")
    public HouseImage getHouseImageById(@PathVariable("id") Long id) {
        Optional<HouseImage> house = houseImageRepository.findById(id);
        HouseImage new_house = new HouseImage();
        if (house.isPresent()) {
            new_house.setHouse_image_id(house.get().getHouse_image_id());
            new_house.setImageName(house.get().getImageName());
            new_house.setImageUrl(ImageUtility.decompressBytes(house.get().getImageUrl()));
            new_house.setHouse(house.get().getHouse());
            return new_house;
        } else {
            return new HouseImage();
        }
    }

    @GetMapping("/by-house-id/{id}")
    public List<HouseImage> findByHouseId(@PathVariable("id") Long id) {
        List<HouseImage> house = houseImageRepository.findByHouseId(id);
        List<HouseImage> new_list = new ArrayList<>();
        if(house.size() > 0){
            for(HouseImage image : house){
                HouseImage new_house = new HouseImage();
                new_house.setHouse_image_id(image.getHouse_image_id());
                new_house.setHouse(image.getHouse());
                new_house.setImageName(image.getImageName());
                new_house.setImageUrl(ImageUtility.decompressBytes(image.getImageUrl()));
                new_list.add(new_house);
            }
        }else{
            return new ArrayList<>();
        }
        return new_list;
    }

    @GetMapping("/allHouseImages")
    public List<HouseImage> getAllHouseImages(){
        List<HouseImage> h = houseImageRepository.findAll();
        List<HouseImage> new_list = new ArrayList<>();
        if(h.size() > 0){
            for(HouseImage image : h){
                HouseImage new_house = new HouseImage();
                new_house.setHouse_image_id(image.getHouse_image_id());
                new_house.setHouse(image.getHouse());
                new_house.setImageName(image.getImageName());
                new_house.setImageUrl(ImageUtility.decompressBytes(image.getImageUrl()));
                new_list.add(new_house);
            }
        }else{
            return new ArrayList<>();
        }
        return new_list;
    }

    @DeleteMapping("/delete-house-image-by-house-id/{id}")
    public HttpStatus deleteHouseImageByHouseId(@PathVariable long id){
        houseImageRepository.deleteHouseImageByHouseId(id);
        return HttpStatus.OK;
    }
}
