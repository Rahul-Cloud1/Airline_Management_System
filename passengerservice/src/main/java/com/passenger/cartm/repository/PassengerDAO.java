package com.passenger.cartm.repository;

import com.passenger.cartm.models.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PassengerDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Passenger> findAll() {
        String sql = "SELECT * FROM passengers";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Passenger.class));
    }

    public Passenger findById(Long id) {
        String sql = "SELECT * FROM passengers WHERE id = ?";
        List<Passenger> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Passenger.class), id);
        return result.isEmpty() ? null : result.get(0);
    }

    public int save(Passenger p) {
        String sql = "INSERT INTO passengers (id, name, email_id) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, p.getId(), p.getName(), p.getEmailId());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM passengers WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
