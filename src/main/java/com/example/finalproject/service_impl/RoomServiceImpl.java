package com.example.finalproject.service_impl;

import com.example.finalproject.dtos.RoomFilterRequest;
import com.example.finalproject.dtos.RoomRequest;
import com.example.finalproject.dtos.RoomResponse;
import com.example.finalproject.exception.AuthenticationException;
import com.example.finalproject.exception.ResourceNotFoundException;
import com.example.finalproject.model.Room;
import com.example.finalproject.repository.BookingRepo;
import com.example.finalproject.repository.HotelRepo;
import com.example.finalproject.repository.RoomRepo;
import com.example.finalproject.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.example.finalproject.model.enums.RoomStatus;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepo repo;

    @Autowired
    HotelRepo hotelRepo;

    @Autowired
    BookingRepo bookingRepo;


    @Override
    public RoomResponse addRoom(RoomRequest request,String role) {
    	
	    if(!role.equals("ADMIN")) {

	        throw new AuthenticationException(
	                "Only admin can add rooms"
	        );
	    }

        hotelRepo.findById(request.getHotelId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hotel not found"));
        Room room = new Room();

        room.setHotelId(request.getHotelId());
        room.setRoomno(request.getRoomNumber());
        room.setRoomtype(request.getRoomType());
        room.setPrice(request.getPricePerNight());
        room.setCapacity(request.getCapacity());
        room.setStatus(request.getStatus());

        Room savedRoom = repo.save(room);

        return mapToResponse(savedRoom);
    }

    @Override
    public List<RoomResponse> getAllRooms() {
        List<Room> rooms = repo.findAll();

        return rooms.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public RoomResponse updateRoom(int id, RoomRequest request, String role) {
    	
	    if(!role.equals("ADMIN")) {

	        throw new AuthenticationException(
	                "Only admin can update room"
	        );
	    }
        Room room = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Room not found"));
        hotelRepo.findById(request.getHotelId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hotel not found"));
        room.setHotelId(request.getHotelId());
        room.setRoomno(request.getRoomNumber());
        room.setRoomtype(request.getRoomType());
        room.setPrice(request.getPricePerNight());
        room.setCapacity(request.getCapacity());
        room.setStatus(request.getStatus());

        Room updatedRoom = repo.save(room);

        return mapToResponse(updatedRoom);
    }

    @Override
    public void deleteRoom(int id, String role) {
    	
	    if(!role.equals("ADMIN")) {

	        throw new AuthenticationException(
	                "Only admin can add hotels"
	        );
	    }
        Room room = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Room not found"));

        repo.delete(room);

    }

//    @Override
//    public List<RoomResponse> getRoomsByHotel(int hotelId, Integer guests, LocalDate checkInDate, LocalDate checkOutDate) {
//        List<Room> rooms = repo.getRoomsByHotel(hotelId);
//        return rooms.stream()
//                .map(this::mapToResponse)
//                .toList();
//    }

    @Override
    public List<RoomResponse> getRoomsByHotel(
            int hotelId,
            Integer guests,
            LocalDate checkInDate,
            LocalDate checkOutDate) {
    	
    	hotelRepo.findById(hotelId)
        .orElseThrow(() ->
                new ResourceNotFoundException("Hotel not found"));

        List<Room> rooms = repo.getRoomsByHotel(hotelId);

        List<RoomResponse> availableRooms = new ArrayList<>();

        for (Room room : rooms) {

            if (room.getStatus() != RoomStatus.AVAILABLE) {
                continue;
            }

            if (guests != null && room.getCapacity() < guests) {
                continue;
            }

            if (checkInDate != null && checkOutDate != null) {

                long count = bookingRepo.countOverlappingBookings(
                        room.getId(),
                        checkInDate,
                        checkOutDate
                );

                if (count > 0) {
                    continue;
                }
            }

            availableRooms.add(mapToResponse(room));
        }

        return availableRooms;
    }

    @Override
    public List<RoomResponse> filterRooms(int hotelId, RoomFilterRequest request) {
        List<Room> rooms = repo.filterRooms(
                hotelId,
                request.getRoomType(),
                request.getCapacity(),
                request.getMaxPrice());

        return rooms.stream()
                .map(this::mapToResponse)
                .toList();
    }

    public RoomResponse mapToResponse(Room room) {

        RoomResponse response = new RoomResponse();

        response.setId(room.getId());
        response.setHotelId(room.getHotelId());
        response.setRoomNumber(room.getRoomno());
        response.setRoomType(room.getRoomtype());
        response.setPricePerNight(room.getPrice());
        response.setCapacity(room.getCapacity());
        response.setStatus(room.getStatus());

        return response;
    }
}
