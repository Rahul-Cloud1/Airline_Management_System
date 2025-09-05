package com.booking.cartm.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.booking.cartm.models.Booking;
import com.booking.cartm.repository.BookingDAO;

@Service
public class BookingService {

    private final BookingDAO bookingDAO;

    public BookingService(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    public List<Booking> getAllBookings() {
        return bookingDAO.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingDAO.findById(id);
    }

    public int saveBooking(Booking booking) {
        return bookingDAO.save(booking);
    }

    public boolean deleteBooking(Long id) {
        return bookingDAO.deleteById(id) > 0;
    }
}
