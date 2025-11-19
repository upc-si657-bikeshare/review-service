package com.bikeshare.review.review;

import com.bikeshare.review.client.BookingClient;
import com.bikeshare.review.client.CatalogClient;
import com.bikeshare.review.client.ReservationResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookingClient bookingClient;
    private final CatalogClient catalogClient;

    @Transactional
    public Review create(CreateReviewRequest dto) {
        ReservationResponse reservation = bookingClient.getReservation(dto.reservationId());

        if (!"COMPLETED".equals(reservation.status())) {
            throw new IllegalStateException("Solo se pueden reseñar reservas completadas.");
        }
        var bike = catalogClient.getBike(reservation.bikeId());

        Review review = Review.builder()
                .reservationId(reservation.id())
                .reviewerId(reservation.renterId())
                .ownerId(bike.ownerId())
                .rating(dto.rating())
                .comment(dto.comment())
                .build();

        return reviewRepository.save(review);
    }

    public List<Review> findReviewsForOwner(Long ownerId) {
        return reviewRepository.findByOwnerId(ownerId);
    }

    public List<Review> findReviewsByRenter(Long reviewerId) {
        return reviewRepository.findByReviewerId(reviewerId);
    }
}