package com.example.finalproject.service_impl;

import com.example.finalproject.dtos.PaymentRequest;
import com.example.finalproject.dtos.PaymentResponse;
import com.example.finalproject.exception.AuthenticationException;
import com.example.finalproject.exception.PaymentFailedException;
import com.example.finalproject.exception.ResourceNotFoundException;
import com.example.finalproject.jwt.JwtUtils;
import com.example.finalproject.model.Booking;
import com.example.finalproject.model.Payment;
import com.example.finalproject.model.enums.BookingStatus;
import com.example.finalproject.model.enums.PaymentStatus;
import com.example.finalproject.repository.BookingRepo;
import com.example.finalproject.repository.PaymentRepo;
import com.example.finalproject.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepo paymentRepository;

    @Autowired
    private BookingRepo bookingRepository;

    @Override
    public PaymentResponse makePayment(PaymentRequest request, int userId) {

        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        if (booking.getUserId() != userId) {
            throw new AuthenticationException("You are not authorized to make payment for this booking");
        }

        if (paymentRepository.findByBookingId(request.getBookingId()).isPresent()) {
            throw new PaymentFailedException("Payment already exists for this booking");
        }

        Payment payment = new Payment();
        payment.setBookingId(request.getBookingId());
        payment.setUserId(userId);
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setAmount(booking.getTotalAmount());
        payment.setStatus(PaymentStatus.SUCCESS);

        Payment savedPayment = paymentRepository.save(payment);
        booking.setStatus(BookingStatus.PAID);
        booking.setPaymentCompleted(true);
        bookingRepository.save(booking);


        PaymentResponse response = new PaymentResponse();
        response.setPaymentId(savedPayment.getId());
        response.setBookingId(savedPayment.getBookingId());
        response.setAmount(booking.getTotalAmount());
        response.setPaymentMethod(savedPayment.getPaymentMethod());
        response.setPaymentStatus(savedPayment.getStatus());

        return response;
    }

    @Override
    public List<PaymentResponse> getMyPayments(int userId) {
        List<Payment> payments = paymentRepository.findByUserId(userId);

        List<PaymentResponse> responses = new ArrayList<>();

        for (Payment payment : payments) {

            Booking booking = bookingRepository.findById(payment.getBookingId())
                    .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

            PaymentResponse response = new PaymentResponse();
            response.setPaymentId(payment.getId());
            response.setBookingId(payment.getBookingId());
            response.setAmount(booking.getTotalAmount());
            response.setPaymentMethod(payment.getPaymentMethod());
            response.setPaymentStatus(payment.getStatus());

            responses.add(response);
        }

        return responses;
    }

    @Override
    public PaymentResponse getPaymentByBooking(int bookingId, int userId) {
        Payment payment = paymentRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));

        if (payment.getUserId() != userId) {
            throw new AuthenticationException("You are not authorized to view this payment");
        }

        Booking booking = bookingRepository.findById(payment.getBookingId())
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        PaymentResponse response = new PaymentResponse();
        response.setPaymentId(payment.getId());
        response.setBookingId(payment.getBookingId());
        response.setAmount(booking.getTotalAmount());
        response.setPaymentMethod(payment.getPaymentMethod());
        response.setPaymentStatus(payment.getStatus());

        return response;
    }


}
