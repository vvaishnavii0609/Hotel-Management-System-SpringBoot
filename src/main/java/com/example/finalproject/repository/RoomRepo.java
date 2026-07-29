package com.example.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finalproject.model.Room;

public interface RoomRepo extends JpaRepository<Room, Integer> {

}
