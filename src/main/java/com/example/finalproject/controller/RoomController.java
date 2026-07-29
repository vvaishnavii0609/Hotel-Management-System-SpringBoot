package com.example.finalproject.controller;

import com.example.finalproject.dtos.RoomFilterRequest;
import com.example.finalproject.dtos.RoomRequest;
import com.example.finalproject.dtos.RoomResponse;
import com.example.finalproject.service.RoomService;
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
            @RequestBody RoomRequest request) {

        RoomResponse response = service.addRoom(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAllRooms() {

        return ResponseEntity.ok(service.getAllRooms());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> updateRoom(
            @PathVariable int id,
            @RequestBody RoomRequest request) {

        return ResponseEntity.ok(
                service.updateRoom(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(
            @PathVariable int id) {

        service.deleteRoom(id);

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

