package com.example.finalproject.dtos;

import java.util.Map;

public class DashboardResponse {


        private long totalHotels;
        private long totalRooms;
        private long totalBookings;
        private long totalPayments;

        private double totalrevenue;

    private Map<String, Long> hotelsByCity;

    private Map<String, Long> bookingStatus;

    public void setTotalrevenue(double totalrevenue) {
        this.totalrevenue = totalrevenue;
    }

    public Map<String, Long> getHotelsByCity() {
        return hotelsByCity;
    }

    public void setHotelsByCity(Map<String, Long> hotelsByCity) {
        this.hotelsByCity = hotelsByCity;
    }

    public Map<String, Long> getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(Map<String, Long> bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public double getTotalrevenue() {
        return totalrevenue;
    }


    public long getTotalHotels() {
        return totalHotels;
    }

    public void setTotalHotels(long totalHotels) {
        this.totalHotels = totalHotels;
    }

    public long getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(long totalRooms) {
        this.totalRooms = totalRooms;
    }

    public long getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(long totalBookings) {
        this.totalBookings = totalBookings;
    }

    public long getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(long totalPayments) {
        this.totalPayments = totalPayments;
    }

    public void setTotalRevenue(double v) {
        this.totalrevenue = totalrevenue;

    }
}
