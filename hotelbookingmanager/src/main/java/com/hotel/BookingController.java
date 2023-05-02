package com.hotel;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class BookingController {
    private HotelBookingManager bookingManager;

    public BookingController(HotelBookingManager bookingManager) {
        this.bookingManager = bookingManager;
    }

    @PostMapping("/bookings")
    public ResponseEntity<Void> addBooking(@RequestBody Booking booking) {
        boolean added = bookingManager.addBooking(booking);
        if (added) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/availability")
    public List<Integer> getAvailableRooms(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return bookingManager.getAvailableRooms(date);
    }

    @GetMapping("/bookings")
    public List<Booking> getBookingsByGuestName(@RequestParam("guestName") String guestName) {
        return bookingManager.getBookingsByGuestName(guestName);
    }
}
