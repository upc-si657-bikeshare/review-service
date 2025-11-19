package com.bikeshare.review.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDateTime;

@FeignClient(name = "booking-service", url = "http://localhost:8083")
public interface BookingClient {
    @GetMapping("/api/reservations/{id}")
    ReservationResponse getReservation(@PathVariable("id") Long id);
}