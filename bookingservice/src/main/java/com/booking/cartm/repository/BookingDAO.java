package com.booking.cartm.repository;

import com.booking.cartm.models.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BookingDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Booking> findAll() {
        String sql = "SELECT * FROM bookings";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Booking.class));
    }

    public Booking findById(Long id) {
        String sql = "SELECT * FROM bookings WHERE id = ?";
        List<Booking> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Booking.class), id);
        return result.isEmpty() ? null : result.get(0);
    }

    public int save(Booking b) {
        String sql = "INSERT INTO bookings (id, flight_number, passenger_name) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, b.getId(), b.getFlightNumber(), b.getPassengerName());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM bookings WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
