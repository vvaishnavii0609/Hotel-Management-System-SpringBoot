package com.example.finalproject.dtos;

public class HotelResponse {
	
	private int id;
	
	private String name;
	
	private String city;
	
	private String description;
	
	private double rating;
	
	private String address;

	
//	private int totalrooms;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public HotelResponse(int id, String name, String city, String description, double rating) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.description = description;
		this.rating = rating;
//		this.totalrooms=totalrooms;
	}

//	public int getTotalrooms() {
//		return totalrooms;
//	}
//
//	public void setTotalrooms(int totalrooms) {
//		this.totalrooms = totalrooms;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "HotelResponse [id=" + id + ", name=" + name + ", city=" + city + ", description=" + description
				+ ", rating=" + rating + "]";
	}

	public HotelResponse() {
		super();
	}

	
}
