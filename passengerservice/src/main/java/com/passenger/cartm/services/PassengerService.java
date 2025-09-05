package com.passenger.cartm.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.passenger.cartm.models.Passenger;
import com.passenger.cartm.repository.PassengerDAO;

@Service
public class PassengerService {

    private final PassengerDAO passengerDAO;

    public PassengerService(PassengerDAO passengerDAO) {
        this.passengerDAO = passengerDAO;
    }

    // ...existing code...

        public List<Passenger> getAllPassengers() {
            return passengerDAO.findAll();
        }

        public Passenger getPassengerById(Long id) {
            return passengerDAO.findById(id);
        }

        public int savePassenger(Passenger passenger) {
            return passengerDAO.save(passenger);
        }

    // ...existing code...

    public boolean deletePassenger(Long id) {
        return passengerDAO.deleteById(id) > 0;
    }
}
