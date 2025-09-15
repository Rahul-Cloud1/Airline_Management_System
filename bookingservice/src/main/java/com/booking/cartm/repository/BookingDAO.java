package com.booking.cartm.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.booking.cartm.models.Booking;

@Repository
public class BookingDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Booking> findAll() {
        String sql = "SELECT * FROM BOOKINGS";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Booking.class));
    }

    public Booking findById(Long id) {
        String sql = "SELECT * FROM BOOKINGS WHERE id = ?";
        List<Booking> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Booking.class), id);
        return result.isEmpty() ? null : result.get(0);
    }

    public int save(Booking b) {
        String sql = "INSERT INTO BOOKINGS (passenger_id, flight_id, booking_date, seat_number, status) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, b.getPassengerId(), b.getFlightId(), b.getBookingDate(), b.getSeatNumber(), b.getStatus());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM BOOKINGS WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int update(Long id, Booking b) {
        String sql = "UPDATE BOOKINGS SET passenger_id = ?, flight_id = ?, booking_date = ?, seat_number = ?, status = ? WHERE id = ?";
        return jdbcTemplate.update(sql, b.getPassengerId(), b.getFlightId(), b.getBookingDate(), b.getSeatNumber(), b.getStatus(), id);
    }

        public List<Booking> findByPassengerId(Long passengerId) {
            String sql = "SELECT * FROM BOOKINGS WHERE passenger_id = ?";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Booking.class), passengerId);
        }
    
    
    }

