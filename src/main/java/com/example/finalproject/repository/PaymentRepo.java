package com.example.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finalproject.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {

    Optional<Payment> findByBookingId(int bookingId);

    List<Payment> findByUserId(int userId);
}
