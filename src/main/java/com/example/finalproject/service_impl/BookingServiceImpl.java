package com.example.finalproject.service_impl;

import com.example.finalproject.dtos.BookingRequest;
import com.example.finalproject.dtos.BookingResponse;
import com.example.finalproject.exception.AuthenticationException;
import com.example.finalproject.exception.InvalidBookingException;
import com.example.finalproject.exception.ResourceNotFoundException;
import com.example.finalproject.exception.RoomUnavailableException;
import com.example.finalproject.model.Booking;
import com.example.finalproject.model.Hotel;
import com.example.finalproject.model.Room;
import com.example.finalproject.model.enums.BookingStatus;
import com.example.finalproject.model.enums.RoomStatus;
import com.example.finalproject.repository.BookingRepo;
import com.example.finalproject.repository.HotelRepo;
import com.example.finalproject.repository.RoomRepo;
import com.example.finalproject.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
   private RoomRepo roomRepo;

    @Autowired
    private HotelRepo hotelRepo;


    @Override
    public BookingResponse createBooking(BookingRequest request, Integer id) {
        Room room = roomRepo.findById(request.getRoomId()) .orElseThrow(() -> new RuntimeException("Room not found"));
        if(room.getStatus()== RoomStatus.MAINTENANCE)
        {
        	throw new RoomUnavailableException("Room is under maintenance");
        }
        if(!request.getCheckInDate().isBefore(request.getCheckOutDate()))
        {
        	throw new InvalidBookingException("Check-in date must be before check-out date");
        }
        if(request.getCheckInDate().isBefore(LocalDate.now()))
        {
        	throw new InvalidBookingException("Check-in date cannot be in the past");
        }
        if(request.getNumberOfGuests()>room.getCapacity())
        {
        	throw new InvalidBookingException("Number of guests exceeds room capacity");
        }
        long count = bookingRepo.countOverlappingBookings(
                room.getId(),
                request.getCheckInDate(),
                request.getCheckOutDate());

        if(count>0)
        {
        	throw new RoomUnavailableException("Room is already booked for the selected dates");
        }
        long numberOfNights = ChronoUnit.DAYS.between(request.getCheckInDate(), request.getCheckOutDate());

        double totalAmount = numberOfNights * room.getPrice();

        Booking booking = new Booking();
        booking.setUserId(id);
        booking.setHotelId(room.getHotelId());
        booking.setRoomId(room.getId());
        booking.setNumberOfGuests(request.getNumberOfGuests());
        booking.setBookingDate(LocalDateTime.now());
        booking.setCheckinDate(request.getCheckInDate());
        booking.setCheckOutDate(request.getCheckOutDate());
        booking.setTotalAmount(totalAmount);
        booking.setStatus(BookingStatus.CONFIRMED);


        Booking savedBooking = bookingRepo.save(booking);
        Hotel hotel = hotelRepo.findById(room.getHotelId())
        		.orElseThrow(() ->
                new ResourceNotFoundException("Hotel not found"));

        return mapToResponse(savedBooking,room,hotel);
    }

    @Override
    public List<BookingResponse> getMyBookings(Integer id) {
        List<Booking> bookings = bookingRepo.findByUserId(id);

        List<BookingResponse> responses = new ArrayList<>();

        for (Booking booking : bookings) {

            System.out.println("---------------------");
            System.out.println("Booking ID : " + booking.getId());
            System.out.println("User ID    : " + booking.getUserId());
            System.out.println("Room ID    : " + booking.getRoomId());


            Room room = roomRepo.findById(booking.getRoomId())
            		.orElseThrow(() ->
                    new ResourceNotFoundException("Room not found"));

            Hotel hotel = hotelRepo.findById(booking.getHotelId())
            		.orElseThrow(() ->
                    new ResourceNotFoundException("Hotel not found"));

            responses.add(mapToResponse(booking, room, hotel));
        }

        return responses;
    }

    @Override
    public BookingResponse getBookingById(int bookingId, Integer id) {
        Booking booking = bookingRepo.findById(bookingId)
        		.orElseThrow(() ->
                new ResourceNotFoundException("Booking not found"));

        if (booking.getUserId()!= id) {
        	throw new AuthenticationException("You are not authorized to view this booking");
        }

        Room room = roomRepo.findById(booking.getRoomId())
        		.orElseThrow(() ->
                new ResourceNotFoundException("Room not found"));

        Hotel hotel = hotelRepo.findById(booking.getHotelId())
        		.orElseThrow(() ->
                new ResourceNotFoundException("Hotel not found"));

        return mapToResponse(booking, room, hotel);
    }

    @Override
    public BookingResponse cancelBooking(int bookingId, Integer id) {
        Booking booking = bookingRepo.findById(bookingId)
        		.orElseThrow(() ->
                new ResourceNotFoundException("Booking not found"));

        if (booking.getUserId()!=id) {
        	throw new AuthenticationException("You are not authorized to view this booking");
        }

        if (booking.getStatus() == BookingStatus.CANCELLED) {
        	throw new InvalidBookingException("Booking is already cancelled");
        }

        booking.setStatus(BookingStatus.CANCELLED);

        Booking updatedBooking = bookingRepo.save(booking);

        Room room = roomRepo.findById(updatedBooking.getRoomId())
        		.orElseThrow(() ->
                new ResourceNotFoundException("Room not found"));

        Hotel hotel = hotelRepo.findById(updatedBooking.getHotelId())
        		.orElseThrow(() ->
                new ResourceNotFoundException("Hotel not found"));

        return mapToResponse(updatedBooking, room, hotel);
    }

    private BookingResponse mapToResponse(
            Booking booking,
            Room room,
            Hotel hotel) {

        BookingResponse response = new BookingResponse();

        response.setBookingId(booking.getId());

        response.setHotelId(hotel.getId());
        response.setHotelName(hotel.getName());

        response.setRoomId(room.getId());
        response.setRoomNumber(room.getRoomno());
        response.setRoomType(room.getRoomtype());

        response.setCheckInDate(booking.getCheckinDate());
        response.setCheckOutDate(booking.getCheckOutDate());

        response.setNumberOfGuests(booking.getNumberOfGuests());

        response.setTotalAmount(booking.getTotalAmount());

        response.setStatus(booking.getStatus());

        response.setBookingDate(booking.getBookingDate());

        return response;
    }
}
