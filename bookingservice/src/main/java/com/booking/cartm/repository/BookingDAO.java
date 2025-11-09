package com.booking.cartm.repository;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.booking.cartm.models.Booking;

@Repository
public class BookingDAO {

    private static final Logger logger = LoggerFactory.getLogger(BookingDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Booking> findAll() {
        String sql = "SELECT * FROM BOOKINGS";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Booking.class));
        } catch (DataAccessException dae) {
            logger.error("Failed to query BOOKINGS.findAll", dae);
            return Collections.emptyList();
        }
    }

    public Booking findById(Long id) {
        String sql = "SELECT * FROM BOOKINGS WHERE id = ?";
        try {
            List<Booking> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Booking.class), id);
            return result.isEmpty() ? null : result.get(0);
        } catch (DataAccessException dae) {
            logger.error("Failed to query BOOKINGS.findById for id={}", id, dae);
            return null;
        }
    }

    public int save(Booking b) {
        String sql = "INSERT INTO BOOKINGS (passenger_id, flight_id, booking_date, seat_number, status) VALUES (?, ?, ?, ?, ?)";
        try {
            return jdbcTemplate.update(sql, b.getPassengerId(), b.getFlightId(), b.getBookingDate(), b.getSeatNumber(), b.getStatus());
        } catch (DataAccessException dae) {
            logger.error("Failed to insert booking: {}", b, dae);
            return 0;
        }
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM BOOKINGS WHERE id = ?";
        try {
            return jdbcTemplate.update(sql, id);
        } catch (DataAccessException dae) {
            logger.error("Failed to delete booking id={}", id, dae);
            return 0;
        }
    }

    public int update(Long id, Booking b) {
        String sql = "UPDATE BOOKINGS SET passenger_id = ?, flight_id = ?, booking_date = ?, seat_number = ?, status = ? WHERE id = ?";
        try {
            return jdbcTemplate.update(sql, b.getPassengerId(), b.getFlightId(), b.getBookingDate(), b.getSeatNumber(), b.getStatus(), id);
        } catch (DataAccessException dae) {
            logger.error("Failed to update booking id={}", id, dae);
            return 0;
        }
    }

    public List<Booking> findByPassengerId(Long passengerId) {
        String sql = "SELECT * FROM BOOKINGS WHERE passenger_id = ?";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Booking.class), passengerId);
        } catch (DataAccessException dae) {
            logger.error("Failed to query BOOKINGS.findByPassengerId for passengerId={}", passengerId, dae);
            return Collections.emptyList();
        }
    }

}

