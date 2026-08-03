package com.example.finalproject.controller;

import java.util.List;

import com.example.finalproject.dtos.HotelResponse;
import com.example.finalproject.dtos.HotelSearchRequest;
import com.example.finalproject.exception.AuthenticationException;

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
import com.example.finalproject.repository.HotelRepo;
import com.example.finalproject.service.HotelService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	
@Autowired
HotelRepo repo;


@Autowired
HotelService service;

    @PostMapping
    public ResponseEntity<HotelResponse> addHotel(
            @RequestBody HotelRequest request, HttpServletRequest httprequest) {
    	
    	String role = (String)httprequest.getAttribute("role");

    	if(role == null){
    	    throw new AuthenticationException("Login required");
    	}

    	HotelResponse hotel = service.addHotel(request,role);


        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }

//    @PostMapping
//    public ResponseEntity<HotelResponse> addHotel(@RequestBody HotelRequest request) {
//        return ResponseEntity.ok(service.addHotel(request));
//    }



@GetMapping
public ResponseEntity<List<HotelResponse>> getAllHotels() {

    return ResponseEntity.ok(service.getAllHotels());
}

@GetMapping("/{id}")
public ResponseEntity<HotelResponse> getHotelById(@PathVariable int id) {

    HotelResponse hotel = service.getHotelById(id);

    return ResponseEntity.ok(hotel);
}

@PutMapping("/{id}")
public ResponseEntity<HotelResponse> updateHotel( @PathVariable int id, @RequestBody HotelRequest request, HttpServletRequest httprequest) {

	String role = (String)httprequest.getAttribute("role");

	if(role == null){
	    throw new AuthenticationException("Login required");
	}


    HotelResponse hotel = service.updateHotel(id, request, role);

    return ResponseEntity.ok(hotel);
}


@DeleteMapping("/{id}")
public ResponseEntity<String> deleteHotel(@PathVariable int id, HttpServletRequest httprequest) {

	String role = (String)httprequest.getAttribute("role");
	if(role == null){
	    throw new AuthenticationException("Login required");
	}
 service.deleteHotel(id,role);

    return ResponseEntity.ok("Hotel deleted successfully"
    );
}

@GetMapping("/filter")
public ResponseEntity<List<HotelResponse>> filterHotels(
        @ModelAttribute HotelFilterRequest request) {

    return ResponseEntity.ok(service.filterHotels(request));

}
    @PostMapping("/search")
    public ResponseEntity<List<HotelResponse>> searchHotels(
            @RequestBody HotelSearchRequest request) {

        return ResponseEntity.ok(service.searchHotels(request));
    }

}
