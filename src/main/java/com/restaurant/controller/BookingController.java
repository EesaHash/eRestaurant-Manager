package com.restaurant.controller;

import com.restaurant.repository.BookingRepo;
import com.restaurant.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){this.bookingService=bookingService;}

}
