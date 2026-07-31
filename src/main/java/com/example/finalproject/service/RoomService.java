package com.example.finalproject.service;

import com.example.finalproject.dtos.RoomFilterRequest;
import com.example.finalproject.dtos.RoomRequest;
import com.example.finalproject.dtos.RoomResponse;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    RoomResponse addRoom(RoomRequest request, String role);

    List<RoomResponse> getAllRooms();

    RoomResponse updateRoom(int id, RoomRequest request,String role);

    void deleteRoom(int id,String role);

    List<RoomResponse> getRoomsByHotel(int hotelId, Integer guests, LocalDate checkInDate, LocalDate checkOutDate);

    List<RoomResponse> filterRooms(int hotelId, RoomFilterRequest request);
}
