package com.example.finalproject.model;

import java.time.LocalDate;
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
	
	private boolean paymentCompleted;



	private int hotelId;

	private int numberOfGuests;

	private LocalDateTime bookingDate;

	private LocalDate checkinDate;

	private LocalDate checkOutDate;

	private double totalAmount;

	@Enumerated(EnumType.STRING)
	private BookingStatus status;

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelid) {
		this.hotelId = hotelid;
	}

	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}
	
	public boolean isPaymentCompleted() {
	    return paymentCompleted;
	}

	public void setPaymentCompleted(boolean paymentCompleted) {
	    this.paymentCompleted = paymentCompleted;
	}


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

	public LocalDate getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(LocalDate checkinDate) {
		this.checkinDate = checkinDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
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

	public Booking(int id, int userId, int roomId, int hotelId,LocalDateTime bookingDate, LocalDate checkinDate,
	               LocalDate checkOutDate, double totalAmount, BookingStatus status , int numberOfGuests ) {
		super();
		this.id = id;
		this.userId = userId;
		this.roomId = roomId;
		this.hotelId=hotelId;
		this.bookingDate = bookingDate;
		this.checkinDate = checkinDate;
		this.checkOutDate = checkOutDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.numberOfGuests= numberOfGuests;
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
