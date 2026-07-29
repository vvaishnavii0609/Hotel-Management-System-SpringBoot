package com.example.finalproject.dtos;

import com.example.finalproject.model.enums.RoomType;

public class RoomFilterRequest {

    private RoomType roomType;

    private Integer capacity;

    private Double maxPrice;

    public RoomFilterRequest(RoomType roomType, Integer capacity, Double maxPrice) {
        this.roomType = roomType;
        this.capacity = capacity;
        this.maxPrice = maxPrice;
    }

    public RoomFilterRequest() {
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public String toString() {
        return "RoomFilterRequest{" +
                "roomType=" + roomType +
                ", capacity=" + capacity +
                ", maxPrice=" + maxPrice +
                '}';
    }
}
