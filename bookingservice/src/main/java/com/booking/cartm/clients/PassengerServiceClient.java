package com.booking.cartm.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "passengerservice")
public interface PassengerServiceClient {
    @GetMapping("/passengers/{id}")
    Object getPassengerById(@PathVariable("id") Long id);
}

