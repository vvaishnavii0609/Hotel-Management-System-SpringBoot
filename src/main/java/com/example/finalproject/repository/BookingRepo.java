package com.example.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.finalproject.model.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer>  {

}
