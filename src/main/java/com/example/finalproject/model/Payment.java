package com.example.finalproject.model;

import java.time.LocalDateTime;

import com.example.finalproject.model.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int bookingId;
	
	private int amount;
	
	private String paymentType;
	
    @Enumerated(EnumType.STRING)
	private PaymentStatus status;
	
	private LocalDateTime paymentDate;

	public Payment(int id, int bookingId, int amount, String paymentType, PaymentStatus status,
			LocalDateTime paymentDate) {
		super();
		this.id = id;
		this.bookingId = bookingId;
		this.amount = amount;
		this.paymentType = paymentType;
		this.status = status;
		this.paymentDate = paymentDate;
	}

	public Payment() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", bookingId=" + bookingId + ", amount=" + amount + ", paymentType=" + paymentType
				+ ", status=" + status + ", paymentDate=" + paymentDate + "]";
	}
	
	
	
	

}
