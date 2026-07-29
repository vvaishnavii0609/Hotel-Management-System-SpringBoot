package com.example.finalproject.controller;

import com.example.finalproject.dtos.PaymentRequest;
import com.example.finalproject.dtos.PaymentResponse;
import com.example.finalproject.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> makePayment(
            @RequestBody PaymentRequest request,
            HttpServletRequest servletRequest) {

        int userId = (Integer) servletRequest.getAttribute("id");

        return ResponseEntity.ok(paymentService.makePayment(request, userId));
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getMyPayments(
            HttpServletRequest servletRequest) {

        int userId = (Integer) servletRequest.getAttribute("id");

        return ResponseEntity.ok(paymentService.getMyPayments(userId));
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<PaymentResponse> getPaymentByBooking(
            @PathVariable int bookingId,
            HttpServletRequest servletRequest) {

        int userId = (Integer) servletRequest.getAttribute("id");

        return ResponseEntity.ok(
                paymentService.getPaymentByBooking(bookingId, userId));
    }
}
