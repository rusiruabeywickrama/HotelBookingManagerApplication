import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.hotel.Booking;
import com.hotel.HotelBookingManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HotelBookingManagerTest {
    private HotelBookingManager bookingManager;

    @BeforeEach
    void setUp() {
        bookingManager = new HotelBookingManager(3);
    }

    @Test
    void testAddBooking() {
        LocalDate date = LocalDate.of(2022, 1, 1);
        Booking booking = new Booking("Alice", 1, date);
        assertTrue(bookingManager.addBooking(booking));
        assertFalse(bookingManager.addBooking(booking));
        assertFalse(bookingManager.addBooking(new Booking("Bob", 1, date)));
    }

    @Test
    void testGetAvailableRooms() {
        LocalDate date = LocalDate.of(2022, 1, 1);
        bookingManager.addBooking(new Booking("Alice", 1, date));
        bookingManager.addBooking(new Booking("Bob", 2, date));
        List<Integer> availableRooms = bookingManager.getAvailableRooms(date);
        assertEquals(1, availableRooms.size());
        bookingManager.addBooking(new Booking("Charlie", 3, date));
        availableRooms = bookingManager.getAvailableRooms(date);
        assertTrue(availableRooms.isEmpty());
    }

    @Test
    void testGetBookingsByGuestName() {
        LocalDate date = LocalDate.of(2022, 1, 1);
        bookingManager.addBooking(new Booking("Alice", 1, date));
        bookingManager.addBooking(new Booking("Bob", 2, date));
        bookingManager.addBooking(new Booking("Alice", 3, date));
        List<Booking> aliceBookings = bookingManager.getBookingsByGuestName("Alice");
        assertEquals(2, aliceBookings.size());
        assertEquals("Alice", aliceBookings.get(0).getGuestName());
        assertEquals("Alice", aliceBookings.get(1).getGuestName());
        List<Booking> bobBookings = bookingManager.getBookingsByGuestName("Bob");
        assertEquals(1, bobBookings.size());
        assertEquals("Bob", bobBookings.get(0).getGuestName());
        List<Booking> charlieBookings = bookingManager.getBookingsByGuestName("Charlie");
        assertTrue(charlieBookings.isEmpty());
    }
}
