package com.semokin.app.adapter.mapper;

import com.semokin.app.adapter.dto.response.ReviewResponse;
import com.semokin.app.domain.model.Review;
import com.semokin.app.domain.model.ReviewPicture;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ReviewMapper {

    public ReviewResponse toResponse(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .customerName(review.getCustomer().getFirstName() + " " + review.getCustomer().getLastName())
                .productId(review.getProduct().getId())
                .rating(review.getRating())
                .comment(review.getComment())
                .pictures(review.getReviewPictures().stream()
                        .map(ReviewPicture::getId)
                        .collect(Collectors.toList()))
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }
}
