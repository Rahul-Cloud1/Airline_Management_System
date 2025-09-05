package com.flight.cartm.repository;

import com.flight.cartm.models.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class FlightDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Flight> findAll() {
        String sql = "SELECT * FROM flights";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Flight.class));
    }

    public Flight findById(Long id) {
        String sql = "SELECT * FROM flights WHERE id = ?";
        List<Flight> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Flight.class), id);
        return result.isEmpty() ? null : result.get(0);
    }

    public int save(Flight f) {
        String sql = "INSERT INTO flights (id, name, quantity) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, f.getId(), f.getName(), f.getQuantity());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM flights WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
