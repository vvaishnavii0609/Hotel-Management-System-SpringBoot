package com.example.finalproject.repository;

import com.example.finalproject.model.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finalproject.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room, Integer> {

    @Query("""
       SELECT r
       FROM Room r
       WHERE r.hotelId = :hotelId
       """)
    List<Room> getRoomsByHotel(@Param("hotelId") Integer hotelId);

    @Query("""
       SELECT r
       FROM Room r
       WHERE (:hotelId IS NULL OR r.hotelId = :hotelId)
       AND (:roomType IS NULL OR r.roomtype = :roomType)
       AND (:capacity IS NULL OR r.capacity >= :capacity)
       AND (:maxPrice IS NULL OR r.price <= :maxPrice)
       """)
    List<Room> filterRooms(
            @Param("hotelId") Integer hotelId,
            @Param("roomType") RoomType roomType,
            @Param("capacity") Integer capacity,
            @Param("maxPrice") Double maxPrice
    );

    boolean existsByHotelId(int hotelId);
}
