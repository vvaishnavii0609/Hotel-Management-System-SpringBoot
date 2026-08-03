package com.example.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.finalproject.model.Booking;
import com.example.finalproject.model.enums.BookingStatus;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer>  {

    List<Booking> findByUserId(int userId);

	@Query("""
SELECT COUNT(b)
FROM Booking b
WHERE b.roomId = :roomId
AND b.status <> com.example.finalproject.model.enums.BookingStatus.CANCELLED
AND b.status <> com.example.finalproject.model.enums.BookingStatus.REJECTED
AND b.checkinDate < :checkOutDate
AND b.checkOutDate > :checkInDate
""")

        long countOverlappingBookings(
                @Param("roomId") int roomId,
                @Param("checkInDate") LocalDate checkInDate,
                @Param("checkOutDate") LocalDate checkOutDate);

	@Query("""
SELECT COUNT(b) > 0
FROM Booking b
WHERE b.roomId = :roomId
""")	boolean existsByRoomId(int roomId);
    
    List<Booking> findByStatus(BookingStatus status);
    
    long countByStatus(BookingStatus status);

	@Query("""
SELECT b.status, COUNT(b)
FROM Booking b
GROUP BY b.status
""")
	List<Object[]> getBookingStatusCount();

}
