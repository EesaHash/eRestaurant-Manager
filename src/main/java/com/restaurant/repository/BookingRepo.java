package com.restaurant.repository;

import com.restaurant.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    void deleteBookingById(Long id);
    //Optional <Booking> findBookingByLName(String lName);
    List<Booking> findBookingByDate(Date date);
}
