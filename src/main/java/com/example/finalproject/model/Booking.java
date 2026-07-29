package com.example.finalproject.model;

import java.time.LocalDateTime;

import com.example.finalproject.model.enums.BookingStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int userId;
	
	private int roomId;
	
	private LocalDateTime bookingDate;
	
	private LocalDateTime checkinDate;

	private LocalDateTime checkOutDate;
	
	private double totalAmount;
	
    @Enumerated(EnumType.STRING)
	private BookingStatus status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalDateTime getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(LocalDateTime checkinDate) {
		this.checkinDate = checkinDate;
	}

	public LocalDateTime getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDateTime checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public Booking(int id, int userId, int roomId, LocalDateTime bookingDate, LocalDateTime checkinDate,
			LocalDateTime checkOutDate, double totalAmount, BookingStatus status) {
		super();
		this.id = id;
		this.userId = userId;
		this.roomId = roomId;
		this.bookingDate = bookingDate;
		this.checkinDate = checkinDate;
		this.checkOutDate = checkOutDate;
		this.totalAmount = totalAmount;
		this.status = status;
	}

	public Booking() {
		super();
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", userId=" + userId + ", roomId=" + roomId + ", bookingDate=" + bookingDate
				+ ", checkinDate=" + checkinDate + ", checkOutDate=" + checkOutDate + ", totalAmount=" + totalAmount
				+ ", status=" + status + "]";
	}
	
	

	
	

}
