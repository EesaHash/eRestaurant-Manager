package com.restaurant.controller;

import com.restaurant.dto.BookingDTO;
import com.restaurant.model.Booking;
import com.restaurant.service.BookingService;
import com.restaurant.service.TableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@Slf4j
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
//
    @GetMapping("/booking/create")
    public String createBooking(Model model){
        model.addAttribute("bookingDTO", new BookingDTO());
        model.addAttribute("minDate", LocalDate.now());
        model.addAttribute("title","New Booking");
        model.addAttribute("submitBtn","Book");
//        Time minTime= Time.valueOf(LocalTime.now());
//        model.addAttribute("minTime",minTime);
        return "booking_create";
    }

    @PostMapping("/booking/create")
    public String saveBooking(@ModelAttribute("bookingDTO") BookingDTO bookingDTO) throws IOException {
        Booking booking= new Booking();
        booking.setId(bookingDTO.getId());
        booking.setNumPeople(bookingDTO.getNumPeople());
        booking.setDate(bookingDTO.getDate());
        booking.setTime(LocalTime.parse(bookingDTO.getTime()));
        booking.setNotes(bookingDTO.getNotes());
        //log.info(String.valueOf("Booking ID"+bookingDTO.getId()));
//        booking.setTableModel(findAvailableTable());
        bookingService.addBooking(booking);
        return "redirect:/booking/view";
    }

    @GetMapping("/booking/edit/{id}")
    public String showUpdateForm(@PathVariable ("id") int id,Model model){
        Booking booking= bookingService.findBookingById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid booking id"+ id));
        BookingDTO bookingDTO=new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setNumPeople(booking.getNumPeople());
        bookingDTO.setDate(new java.sql.Date(booking.getDate().getTime()));
        bookingDTO.setTime(booking.getTime().toString());
        bookingDTO.setNotes(booking.getNotes());
        model.addAttribute("minDate", LocalDate.now());
        model.addAttribute("bookingDTO", bookingDTO);
        model.addAttribute("title","Update Booking");
        model.addAttribute("submitBtn","Update");

        log.info(String.valueOf(bookingDTO.getId()));

//        bookingService.addBooking(booking);
//        model.addAttribute("bookingDTO", bookingService.getAllBookings());
        //return "redirect:/booking/update";
        return "booking_create";
    }

//    @GetMapping("/booking/update/{id}")
//    public String updateBooking(@PathVariable("id") int id, Booking bookingDTO,
//          BindingResult result, Model model) {
//            log.info(bookingDTO.toString());
//            if (result.hasErrors()) {
//                bookingDTO.setId(id);
//                return "booking_update";
//            }
//            bookingService.addBooking(bookingDTO);
//            model.addAttribute("bookingDTO", bookingService.getAllBookings());
//        return"redirect:/booking/view";
//    }

    @GetMapping("/booking/delete/{id}")
    public String deleteBooking(@PathVariable int id){
        bookingService.deleteBooking(id);
        return "redirect:/booking/view";
    }
}