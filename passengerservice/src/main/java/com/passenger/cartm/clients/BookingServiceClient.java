package com.passenger.cartm.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "bookingservice")
public interface BookingServiceClient {
    @GetMapping("/bookings/passenger/{passengerId}")
    Object getBookingsByPassengerId(@PathVariable("passengerId") Long passengerId);
}
