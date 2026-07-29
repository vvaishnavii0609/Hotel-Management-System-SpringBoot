package com.example.finalproject.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.finalproject.model.Hotel;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Integer> {

 @Query("""
            SELECT h
            FROM Hotel h
            WHERE LOWER(h.city)=LOWER(:city)
            """)
 List<Hotel> searchHotelsByCity(@Param("city") String city);

 @Query("""
            SELECT h
            FROM Hotel h
            WHERE (:minRating IS NULL
                   OR h.rating>=:minRating)
            """)
 List<Hotel> filterHotels(
         @Param("minRating") Double minRating);
}
