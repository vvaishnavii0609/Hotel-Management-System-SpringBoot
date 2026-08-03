package com.example.finalproject.controller;

import com.example.finalproject.dtos.BookingRequest;
import com.example.finalproject.dtos.BookingResponse;
import com.example.finalproject.service.BookingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest request, HttpServletRequest httpRequest) {

        Integer userId = (Integer) httpRequest.getAttribute("id");
        BookingResponse response = bookingService.createBooking(request, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getMyBookings(HttpServletRequest httpRequest)
    {
        Integer userId = (Integer) httpRequest.getAttribute("id");
        List<BookingResponse> response = bookingService.getMyBookings(userId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable Integer bookingId, HttpServletRequest httpRequest) {

        Integer userId = (Integer) httpRequest.getAttribute("id");

        BookingResponse response = bookingService.getBookingById(bookingId, userId);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{bookingId}/cancel")
    public ResponseEntity<BookingResponse> cancelBooking(
            @PathVariable Integer bookingId,
            HttpServletRequest httpRequest) {

        Integer userId = (Integer) httpRequest.getAttribute("id");

        BookingResponse response =
                bookingService.cancelBooking(bookingId, userId);

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/admin/pending")
    public ResponseEntity<List<BookingResponse>> getPendingBookings(){

        return ResponseEntity.ok(
            bookingService.getPendingBookings()
        );
    }


    @PatchMapping("/admin/{bookingId}/approve")
    public ResponseEntity<BookingResponse> approveBooking(
            @PathVariable int bookingId){

        return ResponseEntity.ok(
            bookingService.approveBooking(bookingId)
        );
    }


    @PatchMapping("/admin/{bookingId}/reject")
    public ResponseEntity<BookingResponse> rejectBooking(
            @PathVariable int bookingId){

        return ResponseEntity.ok(
            bookingService.rejectBooking(bookingId)
        );
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<BookingResponse>> getAllBookings(){

        return ResponseEntity.ok(
            bookingService.getAllBookings()
        );
    }


}
