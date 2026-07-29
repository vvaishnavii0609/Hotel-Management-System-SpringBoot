package com.example.finalproject.dtos;

import com.example.finalproject.model.enums.RoomStatus;
import com.example.finalproject.model.enums.RoomType;

public class RoomResponse {

    private int id;

    private int hotelId;

    private String roomNumber;

    private RoomType roomType;

    private double pricePerNight;

    private int capacity;

    private RoomStatus status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RoomResponse{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", roomNumber='" + roomNumber + '\'' +
                ", roomType=" + roomType +
                ", pricePerNight=" + pricePerNight +
                ", capacity=" + capacity +
                ", status=" + status +
                '}';
    }

    public RoomResponse(int id, int hotelId, String roomNumber, RoomType roomType, double pricePerNight, int capacity, RoomStatus status) {
        this.id = id;
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
        this.status = status;
    }

    public RoomResponse() {
    }
}
