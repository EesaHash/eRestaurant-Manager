package com.restaurant.controller;

import com.restaurant.model.Booking;
import com.restaurant.repository.BookingRepo;
import com.restaurant.service.BookingService;
import com.restaurant.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class BookingController {
    private final BookingService bookingService;
    private final TableService tableService;

    @Autowired
    public BookingController(BookingService bookingService, TableService tableService){
        this.bookingService=bookingService;
        this.tableService = tableService;
    }

    @GetMapping("/bookings")
    public String getBookings(Model model){
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "bookings";
    }

    @GetMapping("/bookings/{lName}")
    public String getBookingById(String lName){
        bookingService.findBookingByLName(lName);
        return "bookings";
    }

    @GetMapping("/bookings/{date}")
    public String getBookingByDate(Date date){
        bookingService.findBookingByDate(date);
        return "bookings";
    }

    @PostMapping("/bookings/create")
    public String createBooking(@ModelAttribute("booking") Booking booking){
        bookingService.addBooking(booking);
        return "bookings_view";
    }

//    @PutMapping("/bookings/update")
//    public String updateBooking(@PathVariable("booking"))
}
