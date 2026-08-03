package com.example.finalproject.service_impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalproject.dtos.DashboardResponse;
import com.example.finalproject.exception.AuthenticationException;
import com.example.finalproject.repository.BookingRepo;
import com.example.finalproject.repository.HotelRepo;
import com.example.finalproject.repository.PaymentRepo;
import com.example.finalproject.repository.RoomRepo;
import com.example.finalproject.service.DashboardService;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService{
	

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private PaymentRepo paymentRepo;

    @Override
    public DashboardResponse getDashboard(String role) {

        if (!role.equals("ADMIN")) {
            throw new AuthenticationException(
                    "Only admin can access dashboard");
        }

        DashboardResponse response = new DashboardResponse();

        response.setTotalHotels(hotelRepo.count());

        response.setTotalRooms(roomRepo.count());

        response.setTotalBookings(bookingRepo.count());

        response.setTotalPayments(paymentRepo.count());


        Double revenue = paymentRepo.getTotalRevenue();

        response.setTotalRevenue(
                revenue == null ? 0 : revenue);

        Map<String, Long> hotelsByCity = new HashMap<>();

        for (Object[] row : hotelRepo.getHotelsByCity()) {
            hotelsByCity.put((String) row[0], (Long) row[1]);
        }

        response.setHotelsByCity(hotelsByCity);


        Map<String, Long> bookingStatus = new HashMap<>();

        for (Object[] row : bookingRepo.getBookingStatusCount()) {
            bookingStatus.put(row[0].toString(), (Long) row[1]);
        }

        response.setBookingStatus(bookingStatus);
        return response;
    }

		
	}
	
	

