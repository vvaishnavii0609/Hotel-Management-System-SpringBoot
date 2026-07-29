package com.example.finalproject.dtos;

import com.example.finalproject.model.enums.PaymentMethod;

public class PaymentRequest {
    private int bookingId;

    private PaymentMethod paymentMethod;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentRequest() {
    }
}
