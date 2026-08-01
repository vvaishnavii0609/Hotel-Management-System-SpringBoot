package com.example.finalproject.dtos;

public class HotelRequest {
	
	private String name;
	
	private String city;
	
	private String description;
	
	private Double rating;
	
	//private Integer totalrooms;
	

//	public Integer getTotalrooms() {
//		return totalrooms;
//	}
//
//	public void setTotalrooms(Integer totalrooms) {
//		this.totalrooms = totalrooms;
//	}

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

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "HotelRequest [name=" + name + ", city=" + city + ", description=" + description + ", rating=" + rating
				+ "]";
	}

//	public HotelRequest(String name, String city, String description, double rating, int totalRooms) {
//		super();
//		this.name = name;
//		this.city = city;
//		this.description = description;
//		this.rating = rating;
//		this.totalrooms=totalRooms;
//	}

	public HotelRequest() {
		super();
	}
	
	

}
