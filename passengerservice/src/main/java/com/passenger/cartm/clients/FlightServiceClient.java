package com.passenger.cartm.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "flightservice")
public interface FlightServiceClient {
    @GetMapping("/flights/{id}")
    Object getFlightById(@PathVariable("id") Long id);
}
