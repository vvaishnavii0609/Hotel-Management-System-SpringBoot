package com.example.finalproject.dtos;

import java.time.LocalDate;

public class BookingRequest {
    private int roomId;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int numberOfGuests;

//    public BookingRequest(int roomId, LocalDate checkInDate, LocalDate checkOutDate, int numberOfGuests) {
//        this.roomId = roomId;
//        this.checkInDate = checkInDate;
//        this.checkOutDate = checkOutDate;
//        this.numberOfGuests = numberOfGuests;
//    }

    public BookingRequest() {
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
}
