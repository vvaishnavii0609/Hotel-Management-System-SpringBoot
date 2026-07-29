package com.example.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finalproject.model.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {

}
