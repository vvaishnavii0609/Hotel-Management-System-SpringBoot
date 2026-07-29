package com.example.finalproject.dtos;

import java.time.LocalDate;

public class HotelSearchRequest {

    private String city;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int guests;

    public HotelSearchRequest(String city, LocalDate checkInDate, LocalDate checkOutDate, int guests) {
        this.city = city;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.guests = guests;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public HotelSearchRequest() {
    }

    @Override
    public String toString() {
        return "HotelSearchRequest{" +
                "city='" + city + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", guests=" + guests +
                '}';
    }
}
