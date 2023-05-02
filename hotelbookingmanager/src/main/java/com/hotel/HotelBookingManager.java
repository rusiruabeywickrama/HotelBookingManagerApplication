package com.hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class HotelBookingManager {
    private List<Booking> bookings = new ArrayList<>();
    private int numRooms;

    public HotelBookingManager(int numRooms) {
        this.numRooms = numRooms;
    }

    public boolean addBooking(Booking booking) {
        if (bookings.size() < numRooms) { // Check if there are available rooms
            if (isRoomAvailable(booking.getRoomNumber(), booking.getDate())) { // Check if the room is available on the booking date
                bookings.add(booking);
                return true;
            }
        }
        return false;
    }

    private boolean isRoomAvailable(int roomNumber, LocalDate date) {
        for (Booking booking : bookings) {
            if (booking.getRoomNumber() == roomNumber && booking.getDate().equals(date)) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> getAvailableRooms(LocalDate date) {
        List<Integer> availableRooms = new ArrayList<>();
        for (int i = 1; i <= numRooms; i++) { // Check each room
            if (isRoomAvailable(i, date)) {
                availableRooms.add(i);
            }
        }
        return availableRooms;
    }

    public List<Booking> getBookingsByGuestName(String guestName) {
        List<Booking> guestBookings = new ArrayList<>();
        for (Booking booking : bookings) { // Check each booking
            if (booking.getGuestName().equals(guestName)) {
                guestBookings.add(booking);
            }
        }
        return guestBookings;
    }

}
