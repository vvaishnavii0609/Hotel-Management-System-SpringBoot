package com.example.finalproject.service;

import com.example.finalproject.dtos.PaymentRequest;
import com.example.finalproject.dtos.PaymentResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface PaymentService {
    PaymentResponse makePayment(PaymentRequest request, int userId);

    List<PaymentResponse> getMyPayments(int userId);
    PaymentResponse getPaymentByBooking(int bookingId, int userId);
}
