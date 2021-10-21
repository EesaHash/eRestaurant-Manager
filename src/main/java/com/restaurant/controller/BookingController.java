package com.restaurant.controller;

import com.restaurant.dto.BookingDTO;
import com.restaurant.model.Booking;
import com.restaurant.service.BookingService;
import com.restaurant.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Controller
public class BookingController {
    BookingService bookingService;
    TableService tableService;

    @Autowired
    public BookingController(BookingService bookingService, TableService tableService){
        this.bookingService=bookingService;
        this.tableService = tableService;
    }

    @GetMapping("/booking")
    public String bookings(){return "booking";}

    @GetMapping("/booking/view")
    public String getAllBookings(Model model){
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "booking_view";
    }

//    @GetMapping("/booking/{date}")
//    public String getBookingByDate(Date date){
//        bookingService.findBookingByDate(date);
//        return "booking_view";
//    }

    @GetMapping("/booking/create")
    public String createBooking(Model model){
        model.addAttribute("bookingDTO", new BookingDTO());
        model.addAttribute("minDate", LocalDate.now());
//        Time minTime= Time.valueOf(LocalTime.now());
//        model.addAttribute("minTime",minTime);
        return "booking_create";
    }

    @PostMapping("/booking/create")
    public String saveBooking(@ModelAttribute("bookingDTO") BookingDTO bookingDTO) throws IOException {
        Booking booking= new Booking();
        booking.setNumPeople(bookingDTO.getNumPeople());
        booking.setDate(bookingDTO.getDate());
        booking.setTime(LocalTime.parse(bookingDTO.getTime()));
        booking.setNotes(bookingDTO.getNotes());
//        booking.setTableModel(findAvailableTable());
        bookingService.addBooking(booking);
        return "redirect:/booking/view";
    }

    @GetMapping("/booking/edit/{id}")
    public String showUpdateForm(@PathVariable ("id") int id,Model model){
        Booking booking= bookingService.findBookingById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid booking id"+ id));
        BookingDTO bookingDTO=new BookingDTO();
        bookingDTO.setNumPeople(booking.getNumPeople());
        bookingDTO.setDate(new java.sql.Date(booking.getDate().getTime()));
        bookingDTO.setTime(booking.getTime().toString());
        bookingDTO.setNotes(booking.getNotes());
        model.addAttribute("bookingDTO", bookingDTO);
        model.addAttribute("minDate", LocalDate.now());
        return "booking_update";
    }

    @PostMapping("/bookings/update/{id}")
    public String updateBooking(@PathVariable int id, Booking booking, Model model,
          BindingResult result) {
            if (result.hasErrors()) {
                booking.setId(id);
                return "booking_update";
            }
            bookingService.addBooking(booking);
            model.addAttribute("bookingDTO", bookingService.getAllBookings());
        return"redirect:/booking/view";
    }

    @GetMapping("/booking/delete/{id}")
    public String deleteBooking(@PathVariable int id){
        bookingService.deleteBooking(id);
        return "redirect:/booking/view";
    }
}