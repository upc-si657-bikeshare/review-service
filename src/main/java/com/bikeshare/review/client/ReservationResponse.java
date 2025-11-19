package com.bikeshare.review.client;

public record ReservationResponse(
        Long id,
        Long renterId,
        Long bikeId,
        String status
) {}