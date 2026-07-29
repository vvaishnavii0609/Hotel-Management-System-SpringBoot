package com.example.finalproject.service;

import java.util.List;

import com.example.finalproject.dtos.HotelFilterRequest;
import com.example.finalproject.dtos.HotelRequest;
import com.example.finalproject.dtos.HotelResponse;
import com.example.finalproject.dtos.HotelSearchRequest;
import com.example.finalproject.model.Hotel;

public interface HotelService {

	HotelResponse addHotel(HotelRequest request);

	List<HotelResponse> getAllHotels();

	HotelResponse getHotelById(int id);

	HotelResponse updateHotel(int id, HotelRequest request);

	void deleteHotel(int id);

	List<HotelResponse> searchHotels(HotelSearchRequest request);

	List<HotelResponse> filterHotels(HotelFilterRequest request);


}
