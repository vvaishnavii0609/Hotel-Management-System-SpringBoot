package com.example.finalproject.service_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalproject.dtos.HotelFilterRequest;
import com.example.finalproject.dtos.HotelRequest;
import com.example.finalproject.dtos.HotelResponse;
import com.example.finalproject.model.Hotel;
import com.example.finalproject.repository.HotelRepo;
import com.example.finalproject.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	
	@Autowired
	HotelRepo repo;
	
	@Override
	public Hotel addHotel(HotelRequest request) {
		// TODO Auto-generated method stub
		
		Hotel hotel = new Hotel();
		hotel.setName(request.getName());
        hotel.setCity(request.getCity());
        hotel.setDescription(request.getDescription());
        hotel.setRating(request.getRating());
        hotel.setTotalrooms(request.getTotalrooms());
        
        hotel.setAvailableRooms(request.getTotalrooms());
        
      Hotel hotel2 = repo.save(hotel);
     return hotel2;
       
	}

	@Override
	public List<Hotel> getAllHotels() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Hotel getHotelById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Override
	public Hotel updateHotel(int id, HotelRequest request) {
		Hotel hotel = repo.findById(id).get();
		
	    hotel.setName(request.getName());
	    hotel.setCity(request.getCity());
	    hotel.setDescription(request.getDescription());
	    hotel.setRating(request.getRating());
	    hotel.setTotalrooms(request.getTotalrooms());


	    return repo.save(hotel);
	}

	@Override
	public void deleteHotel(int id) {
		// TODO Auto-generated method stub
		
		repo.deleteById(id);
		
	}


	@Override
	public List<Hotel> filterHotels(
	        HotelFilterRequest request) {


	    List<Hotel> hotels = repo.findAll();


	    if(request.getCity() != null
	            && !request.getCity().isEmpty()) {


	        hotels = hotels.stream()
	                .filter(hotel ->
	                    hotel.getCity()
	                    .equalsIgnoreCase(request.getCity())
	                )
	                .toList();

	    }


	    if(request.getRating() != null) {


	        hotels = hotels.stream()
	                .filter(hotel ->
	                    hotel.getRating() >= request.getRating()
	                )
	                .toList();

	    }


	    return hotels;

	}



}
