package com.example.finalproject.service;

import com.example.finalproject.dtos.BookingRequest;
import com.example.finalproject.dtos.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse createBooking(BookingRequest request , Integer id);

    List<BookingResponse> getMyBookings(Integer id);

    BookingResponse getBookingById(int bookingId, Integer id);

    BookingResponse cancelBooking(int bookingId, Integer id);
    
    List<BookingResponse> getPendingBookings(String role);

    BookingResponse approveBooking(int bookingId, String role);

    BookingResponse rejectBooking(int bookingId,String role);
    
    List<BookingResponse> getAllBookings(String role);


}
