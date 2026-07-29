package com.example.finalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalproject.dtos.HotelFilterRequest;
import com.example.finalproject.dtos.HotelRequest;
import com.example.finalproject.model.Hotel;
import com.example.finalproject.repository.HotelRepo;
import com.example.finalproject.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	
@Autowired
HotelRepo repo;


@Autowired
HotelService service;


@PostMapping
public ResponseEntity<Hotel> addHotel(
        @RequestBody HotelRequest request) {


    Hotel hotel = service.addHotel(request);

    return new ResponseEntity<>(
            hotel,
            HttpStatus.CREATED
    );
    

}

@GetMapping
public ResponseEntity<List<Hotel>> getAllHotels() {

    List<Hotel> hotels = service.getAllHotels();

    return ResponseEntity.ok(hotels);
}

@GetMapping("/{id}")
public ResponseEntity<Hotel> getHotelById(@PathVariable int id) {

    Hotel hotel = service.getHotelById(id);

    return ResponseEntity.ok(hotel);
}

@PutMapping("/{id}")
public ResponseEntity<Hotel> updateHotel( @PathVariable int id, @RequestBody HotelRequest request) {

    Hotel hotel = service.updateHotel(id, request);

    return ResponseEntity.ok(hotel);
}


@DeleteMapping("/{id}")
public ResponseEntity<String> deleteHotel(@PathVariable int id) {

 service.deleteHotel(id);

    return ResponseEntity.ok("Hotel deleted successfully"
    );
}

@GetMapping("/filter")
public ResponseEntity<List<Hotel>> filterHotels(
        @ModelAttribute HotelFilterRequest request) {


    return ResponseEntity.ok(
            service.filterHotels(request)
    );

}

}
