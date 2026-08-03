package com.example.finalproject.service_impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.finalproject.dtos.HotelSearchRequest;
import com.example.finalproject.exception.AuthenticationException;
import com.example.finalproject.exception.ResourceNotFoundException;

import com.example.finalproject.repository.RoomRepo;
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

	@Autowired
	RoomRepo roomRepo;
	
	@Override
	public HotelResponse addHotel(HotelRequest request, String role) {
		
		
		// TODO Auto-generated method stub
	    if(!"ADMIN".equals(role)) {

	        throw new AuthenticationException(
	                "Only admin can add hotels"
	        );
	    }
		Hotel hotel = new Hotel();
		hotel.setName(request.getName());
        hotel.setCity(request.getCity());
        hotel.setDescription(request.getDescription());
        hotel.setRating(request.getRating());
        hotel.setAddress(request.getAddress());


//        hotel.setAvailableRooms(request.getTotalrooms());
        
      Hotel hotel2 = repo.save(hotel);
     return mapToResponse(hotel2);
       
	}

	@Override
	public List<HotelResponse> getAllHotels() {
		// TODO Auto-generated method stub
		List<Hotel> hotels = repo.findAll();

		return hotels.stream()
				.map(this::mapToResponse)
				.toList();

	}

	@Override
	public HotelResponse getHotelById(int id) {
		// TODO Auto-generated method stub
		Hotel hotel = repo.findById(id)
		        .orElseThrow(() ->
		                new ResourceNotFoundException("Hotel not found"));

	return mapToResponse(hotel);
	}

	@Override
	public HotelResponse updateHotel(int id, HotelRequest request, String role) {
		
	    if(!"ADMIN".equals(role)) {

	        throw new AuthenticationException(
	                "Only admin can update hotels"
	        );
	    }
		Hotel hotel = repo.findById(id)
		        .orElseThrow(() ->
		                new ResourceNotFoundException("Hotel not found"));
		
	    hotel.setName(request.getName());
	    hotel.setCity(request.getCity());
	    hotel.setDescription(request.getDescription());
	    hotel.setRating(request.getRating());
	    hotel.setAddress(request.getAddress());


	    Hotel updatedhotel= repo.save(hotel);
		return mapToResponse(updatedhotel);
	}

	@Override
	public void deleteHotel(int id, String role) {
		
	    if(!"ADMIN".equals(role)) {

	        throw new AuthenticationException(
	                "Only admin can delete hotels"
	        );
	    }
		Hotel hotel = repo.findById(id)
		        .orElseThrow(() ->
		                new ResourceNotFoundException("Hotel not found"));

		if (roomRepo.existsByHotelId(id)) {

			throw new RuntimeException(
					"Cannot delete hotel because rooms already exist."
			);

		}
		repo.deleteById(id);
		
	}

	@Override
	public List<HotelResponse> searchHotels(HotelSearchRequest request) {
		if (request.getCity() == null || request.getCity().isBlank()) {
			throw new RuntimeException("City is required.");
		}

		if (request.getCheckInDate() == null ||
				request.getCheckOutDate() == null) {
			throw new RuntimeException("Check-in and Check-out dates are required.");
		}

		if (request.getCheckInDate().isAfter(request.getCheckOutDate())) {
			throw new RuntimeException("Check-out date must be after Check-in date.");
		}

		if (request.getGuests() <= 0) {
			throw new RuntimeException("Guests must be greater than zero.");
		}

		List<Hotel> hotels = repo.searchHotelsByCity(request.getCity());

		return hotels.stream()
				.map(this::mapToResponse)
				.toList();
	}


	@Override
	public List<HotelResponse> filterHotels(
			HotelFilterRequest request) {

		List<Hotel> hotels =
				repo.filterHotels(
						request.getCity(),
						request.getMinRating());

//		if(request.getSortBy()!=null) {
//
//			switch(request.getSortBy().toLowerCase()) {
//
//				case "rating":
//
//					hotels.sort(
//							Comparator.comparing(
//									Hotel::getRating));
//
//					break;
//
//				case "name":
//
//					hotels.sort(
//							Comparator.comparing(
//									Hotel::getName));
//
//					break;
//			}
//
//		}

//		if(request.getSortDirection()!=null &&
//				request.getSortDirection()
//						.equalsIgnoreCase("desc")) {
//
//			Collections.reverse(hotels);
//
//		}

		return hotels.stream()

				.map(this::mapToResponse)

				.toList();
	}
	public HotelResponse mapToResponse(Hotel hotel) {

		HotelResponse response = new HotelResponse();

		response.setId(hotel.getId());

		response.setName(hotel.getName());

		response.setCity(hotel.getCity());

		response.setDescription(hotel.getDescription());

		response.setRating(hotel.getRating());
		
		response.setAddress(hotel.getAddress());


//		response.setAvailableRooms(hotel.getAvailableRooms());

		return response;
	}



}
