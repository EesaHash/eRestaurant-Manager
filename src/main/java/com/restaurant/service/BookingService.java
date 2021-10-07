package com.restaurant.service;

import com.restaurant.model.Booking;
import com.restaurant.repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingService {
    private final BookingRepo bookingRepo;

    @Autowired
    public BookingService (BookingRepo bookingRepo){
        this.bookingRepo=bookingRepo;
    }

    public Booking addBooking(Booking booking){ return bookingRepo.save(booking);}

    //public Optional<Booking> findBookingByLName(String lName){return bookingRepo.findBookingByLName(lName);}

    public List<Booking> findBookingByDate(Date date){return  bookingRepo.findBookingByDate(date);}

    public List<Booking> getAllBookings(){return  bookingRepo.findAll();}

    public Booking updateBooking(Booking booking){return bookingRepo.save(booking);}

    public void deleteBooking(Long id) { bookingRepo.deleteBookingById((id));}
}
