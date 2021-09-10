package com.restaurant.repository;

import com.restaurant.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    void deleteBookingById(Long id);

    List<Booking> findBookingByLName(String lName);
}
