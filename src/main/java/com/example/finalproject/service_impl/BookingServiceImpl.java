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
    public BookingResponse createBooking(
            BookingRequest request,
            Integer id) {


        Room room = roomRepo.findById(request.getRoomId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Room not found"));



        if(room.getStatus()== RoomStatus.MAINTENANCE)
        {
            throw new RoomUnavailableException(
                    "Room is under maintenance");
        }



        if(!request.getCheckInDate()
                .isBefore(request.getCheckOutDate()))
        {
            throw new InvalidBookingException(
                    "Check-in date must be before check-out date");
        }



        if(request.getCheckInDate()
                .isBefore(LocalDate.now()))
        {
            throw new InvalidBookingException(
                    "Check-in date cannot be in the past");
        }



        if(request.getNumberOfGuests()
                > room.getCapacity())
        {
            throw new InvalidBookingException(
                    "Number of guests exceeds room capacity");
        }



        long count =
                bookingRepo.countOverlappingBookings(
                        room.getId(),
                        request.getCheckInDate(),
                        request.getCheckOutDate()
                );


        if(count > 0)
        {
            throw new RoomUnavailableException(
                    "Room is already booked for selected dates");
        }



        long nights =
                ChronoUnit.DAYS.between(
                        request.getCheckInDate(),
                        request.getCheckOutDate()
                );


        double totalAmount =
                nights * room.getPrice();



        Booking booking = new Booking();

        booking.setUserId(id);

        booking.setHotelId(room.getHotelId());

        booking.setRoomId(room.getId());

        booking.setNumberOfGuests(
                request.getNumberOfGuests());

        booking.setBookingDate(
                LocalDateTime.now());

        booking.setCheckinDate(
                request.getCheckInDate());

        booking.setCheckOutDate(
                request.getCheckOutDate());

        booking.setTotalAmount(totalAmount);


        // IMPORTANT
        booking.setStatus(
                BookingStatus.PAYMENT_PENDING);


        booking.setPaymentCompleted(false);



        Booking saved =
                bookingRepo.save(booking);



        Hotel hotel =
                hotelRepo.findById(room.getHotelId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Hotel not found"));



        return mapToResponse(
                saved,
                room,
                hotel
        );
    }





    @Override
    public List<BookingResponse> getMyBookings(Integer id) {


        List<Booking> bookings =
                bookingRepo.findByUserId(id);



        List<BookingResponse> responses =
                new ArrayList<>();



        for(Booking booking : bookings)
        {

            Room room =
                    roomRepo.findById(
                            booking.getRoomId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Room not found"));



            Hotel hotel =
                    hotelRepo.findById(
                            booking.getHotelId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Hotel not found"));



            responses.add(
                    mapToResponse(
                            booking,
                            room,
                            hotel));
        }


        return responses;
    }






    @Override
    public BookingResponse getBookingById(
            int bookingId,
            Integer id) {



        Booking booking =
                bookingRepo.findById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Booking not found"));



        if(booking.getUserId()!=id)
        {
            throw new AuthenticationException(
                    "Not authorized");
        }



        Room room =
                roomRepo.findById(
                        booking.getRoomId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Room not found"));



        Hotel hotel =
                hotelRepo.findById(
                        booking.getHotelId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Hotel not found"));



        return mapToResponse(
                booking,
                room,
                hotel);
    }






    @Override
    public BookingResponse cancelBooking(
            int bookingId,
            Integer id) {



        Booking booking =
                bookingRepo.findById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Booking not found"));



        if(booking.getUserId()!=id)
        {
            throw new AuthenticationException(
                    "Not authorized");
        }



        if(booking.getStatus()
                == BookingStatus.CONFIRMED)
        {
            throw new InvalidBookingException(
                    "Confirmed booking cannot be cancelled");
        }



        booking.setStatus(
                BookingStatus.CANCELLED);



        Booking updated =
                bookingRepo.save(booking);



        Room room =
                roomRepo.findById(
                        updated.getRoomId())
                .orElseThrow();



        Hotel hotel =
                hotelRepo.findById(
                        updated.getHotelId())
                .orElseThrow();



        return mapToResponse(
                updated,
                room,
                hotel);
    }







    // ADMIN
    @Override
    public List<BookingResponse> getPendingBookings() {


        List<Booking> bookings =
                bookingRepo.findByStatus(
                        BookingStatus.PAID);



        List<BookingResponse> responses =
                new ArrayList<>();



        for(Booking booking : bookings)
        {

            Room room =
                    roomRepo.findById(
                            booking.getRoomId())
                    .orElseThrow();



            Hotel hotel =
                    hotelRepo.findById(
                            booking.getHotelId())
                    .orElseThrow();



            responses.add(
                    mapToResponse(
                            booking,
                            room,
                            hotel));
        }



        return responses;
    }







    @Override
    public BookingResponse approveBooking(
            int bookingId) {



        Booking booking =
                bookingRepo.findById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Booking not found"));



        if(booking.getStatus()
                != BookingStatus.PAID)
        {
            throw new InvalidBookingException(
                    "Payment not completed");
        }



        booking.setStatus(
                BookingStatus.CONFIRMED);



        Booking updated =
                bookingRepo.save(booking);



        Room room =
                roomRepo.findById(
                        updated.getRoomId())
                .orElseThrow();



        Hotel hotel =
                hotelRepo.findById(
                        updated.getHotelId())
                .orElseThrow();



        return mapToResponse(
                updated,
                room,
                hotel);
    }







    @Override
    public BookingResponse rejectBooking(
            int bookingId) {


        Booking booking =
                bookingRepo.findById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Booking not found"));



        booking.setStatus(
                BookingStatus.REJECTED);



        Booking updated =
                bookingRepo.save(booking);



        Room room =
                roomRepo.findById(
                        updated.getRoomId())
                .orElseThrow();



        Hotel hotel =
                hotelRepo.findById(
                        updated.getHotelId())
                .orElseThrow();



        return mapToResponse(
                updated,
                room,
                hotel);
    }






    @Override
    public List<BookingResponse> getAllBookings() {


        List<Booking> bookings =
                bookingRepo.findAll();



        List<BookingResponse> responses =
                new ArrayList<>();



        for(Booking booking : bookings)
        {

            Room room =
                    roomRepo.findById(
                            booking.getRoomId())
                    .orElseThrow();



            Hotel hotel =
                    hotelRepo.findById(
                            booking.getHotelId())
                    .orElseThrow();



            responses.add(
                    mapToResponse(
                            booking,
                            room,
                            hotel));
        }


        return responses;
    }







    private BookingResponse mapToResponse(
            Booking booking,
            Room room,
            Hotel hotel) {


        BookingResponse response =
                new BookingResponse();



        response.setBookingId(
                booking.getId());


        response.setUserId(
                booking.getUserId());


        response.setHotelId(
                hotel.getId());


        response.setHotelName(
                hotel.getName());


        response.setRoomId(
                room.getId());


        response.setRoomNumber(
                room.getRoomno());


        response.setRoomType(
                room.getRoomtype());


        response.setCheckInDate(
                booking.getCheckinDate());


        response.setCheckOutDate(
                booking.getCheckOutDate());


        response.setNumberOfGuests(
                booking.getNumberOfGuests());


        response.setTotalAmount(
                booking.getTotalAmount());


        response.setStatus(
                booking.getStatus());


        response.setBookingDate(
                booking.getBookingDate());


        response.setPaymentCompleted(
                booking.isPaymentCompleted());



        return response;
    }

}
