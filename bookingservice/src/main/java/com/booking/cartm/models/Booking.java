
package com.booking.cartm.models;

import java.time.LocalDateTime;

public class Booking {
    private Long id;
    private Long passengerId;
    private Long flightId;
    private LocalDateTime bookingDate;
    private String seatNumber;
    private String status;

    public Booking() {}

    public Booking(Long id, Long passengerId, Long flightId, LocalDateTime bookingDate, String seatNumber, String status) {
        this.id = id;
        this.passengerId = passengerId;
        this.flightId = flightId;
        this.bookingDate = bookingDate;
        this.seatNumber = seatNumber;
        this.status = status;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getPassengerId() {
        return passengerId;
    }
    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public Long getFlightId() {
        return flightId;
    }
    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getSeatNumber() {
        return seatNumber;
    }
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
