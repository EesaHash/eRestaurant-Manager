package com.restaurant.repository;

import com.restaurant.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
    List<Booking> findBookingByDate(Date date);
}
