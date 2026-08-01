package com.example.finalproject.controller;

import com.example.finalproject.dtos.RoomFilterRequest;
import com.example.finalproject.dtos.RoomRequest;
import com.example.finalproject.dtos.RoomResponse;
import com.example.finalproject.service.RoomService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService service;

    @PostMapping
    public ResponseEntity<RoomResponse> addRoom(
            @RequestBody RoomRequest request, HttpServletRequest httprequest) {
    	
    	String role = (String) httprequest.getAttribute("role");

        RoomResponse response = service.addRoom(request, role);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAllRooms() {

        return ResponseEntity.ok(service.getAllRooms());
    }
    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable int id){

        return ResponseEntity.ok(service.getRoomById(id));

    }
    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> updateRoom(
            @PathVariable int id,
            @RequestBody RoomRequest request , HttpServletRequest httprequest) {
    	
    	String role = (String) httprequest.getAttribute("role");

        return ResponseEntity.ok(
                service.updateRoom(id, request,role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(
            @PathVariable int id, HttpServletRequest httprequest) {
    	
    	String role = (String) httprequest.getAttribute("role");

        service.deleteRoom(id,role);

        return ResponseEntity.ok("Room deleted successfully");
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RoomResponse>> getRoomsByHotel(
            @PathVariable int hotelId,

            @RequestParam(required = false)
            Integer guests,

            @RequestParam(required = false)
            LocalDate checkInDate,

            @RequestParam(required = false)
            LocalDate checkOutDate) {

        return ResponseEntity.ok(

                service.getRoomsByHotel(
                        hotelId,
                        guests,
                        checkInDate,
                        checkOutDate
                )

        );
    }
    @GetMapping("/hotel/{hotelId}/filter")
    public ResponseEntity<List<RoomResponse>> filterRooms(
            @PathVariable int hotelId,
            @ModelAttribute RoomFilterRequest request) {

        return ResponseEntity.ok(
                service.filterRooms(hotelId, request));
    }
}

