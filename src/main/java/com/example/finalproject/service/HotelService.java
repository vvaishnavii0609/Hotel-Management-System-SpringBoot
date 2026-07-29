package com.example.finalproject.service;

import java.util.List;

import com.example.finalproject.dtos.HotelFilterRequest;
import com.example.finalproject.dtos.HotelRequest;
import com.example.finalproject.dtos.HotelResponse;
import com.example.finalproject.model.Hotel;

public interface HotelService {
	
	Hotel addHotel(HotelRequest request);

	List<Hotel> getAllHotels();

	Hotel getHotelById(int id);

	Hotel updateHotel(int id, HotelRequest request);

	void deleteHotel(int id);

    List<Hotel> filterHotels(
            HotelFilterRequest request
    );


}
