package com.example.finalproject.service;

import java.util.List;

import com.example.finalproject.dtos.HotelFilterRequest;
import com.example.finalproject.dtos.HotelRequest;
import com.example.finalproject.dtos.HotelResponse;
import com.example.finalproject.dtos.HotelSearchRequest;
import com.example.finalproject.model.Hotel;

public interface HotelService {

	HotelResponse addHotel(HotelRequest request, String role);

	List<HotelResponse> getAllHotels();

	HotelResponse getHotelById(int id);

	HotelResponse updateHotel(int id, HotelRequest request, String role);

	void deleteHotel(int id, String role);

	List<HotelResponse> searchHotels(HotelSearchRequest request);

	List<HotelResponse> filterHotels(HotelFilterRequest request);


}
