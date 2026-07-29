package com.example.finalproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String city;
	private String description;
	
	private double rating;
	
	private int totalrooms;
	
	private int availableRooms;

	public int getAvailableRooms() {
		return availableRooms;
	}

	public void setAvailableRooms(int availableRooms) {
		this.availableRooms = availableRooms;
	}

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

	public int getTotalrooms() {
		return totalrooms;
	}

	public void setTotalrooms(int totalrooms) {
		this.totalrooms = totalrooms;
	}

	public Hotel(int id, String name, String city, String description, double rating, int totalrooms, int availableRooms) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.description = description;
		this.rating = rating;
		this.totalrooms = totalrooms;
		this.availableRooms= availableRooms;
	}

	public Hotel() {
		super();
	}

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + ", city=" + city + ", description=" + description + ", rating="
				+ rating + ", totalrooms=" + totalrooms + "]";
	}
	
	

}
