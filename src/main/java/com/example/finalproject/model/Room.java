package com.example.finalproject.model;

import com.example.finalproject.model.enums.RoomStatus;
import com.example.finalproject.model.enums.RoomType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;
	private int hotelId;
	private String roomno;
	
    @Enumerated(EnumType.STRING)
	private RoomType roomtype;
	
	private double price;
	
	private int capacity;
	
    @Enumerated(EnumType.STRING)
	private RoomStatus status;

//	private String bedType;
//
//	private  String amenities;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getRoomno() {
		return roomno;
	}

	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}

	public RoomType getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(RoomType roomtype) {
		this.roomtype = roomtype;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public RoomStatus getStatus() {
		return status;
	}

	public void setStatus(RoomStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", hotelId=" + hotelId + ", roomno=" + roomno + ", roomtype=" + roomtype + ", price="
				+ price + ", capacity=" + capacity + ", status=" + status + "]";
	}

	public Room(int id, int hotelId, String roomno, RoomType roomtype, int price, int capacity, RoomStatus status) {
		super();
		this.id = id;
		this.hotelId = hotelId;
		this.roomno = roomno;
		this.roomtype = roomtype;
		this.price = price;
		this.capacity = capacity;
		this.status = status;

	}

	public Room() {
		super();
	}
//
//	public String getBedType() {
//		return bedType;
//	}
//
//	public void setBedType(String bedType) {
//		this.bedType = bedType;
//	}
//
//	public String getAmenities() {
//		return amenities;
//	}
//
//	public void setAmenities(String amenities) {
//		this.amenities = amenities;
//	}
}
