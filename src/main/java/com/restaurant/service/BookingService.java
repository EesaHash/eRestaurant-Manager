package com.restaurant.service;

import com.restaurant.dto.BookingDTO;
import com.restaurant.model.Booking;
import com.restaurant.repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.*;

@Service
public class BookingService {
    @Autowired
    private final BookingRepo bookingRepo;

    public BookingService (BookingRepo bookingRepo){
        this.bookingRepo=bookingRepo;
    }

    public Booking addBooking(Booking booking){ return bookingRepo.save(booking);}

    //public Optional<Booking> findBookingByLName(String lName){return bookingRepo.findBookingByLName(lName);}
    public Optional<Booking> findBookingById(int id){return bookingRepo.findById(id);}

    public List<Booking> findBookingByDate(Date date){return  bookingRepo.findBookingByDate(date);}

    public List<Booking> getAllBookings(){return  bookingRepo.findAll();}

   // public Booking updateBooking(BookingDTO bookingDTO){return bookingRepo.save(bookingDTO);}

    public void deleteBooking(int id) { bookingRepo.deleteById(id);}
}
