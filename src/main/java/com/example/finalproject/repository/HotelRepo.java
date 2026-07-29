package com.example.finalproject.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.finalproject.model.Hotel;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Integer> {
	
 List<Hotel>findBycity(String city);

}
