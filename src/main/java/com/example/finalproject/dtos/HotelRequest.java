package com.example.finalproject.dtos;

public class HotelRequest {
	
	private String name;
	
	private String city;
	
	private String description;
	
	private double rating;
	
	private int totalrooms;
	

	public int getTotalrooms() {
		return totalrooms;
	}

	public void setTotalrooms(int totalrooms) {
		this.totalrooms = totalrooms;
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
		return "HotelRequest [name=" + name + ", city=" + city + ", description=" + description + ", rating=" + rating
				+ "]";
	}

	public HotelRequest(String name, String city, String description, double rating, int totalRooms) {
		super();
		this.name = name;
		this.city = city;
		this.description = description;
		this.rating = rating;
		this.totalrooms=totalRooms;
	}

	public HotelRequest() {
		super();
	}
	
	

}
