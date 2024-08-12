package com.semokin.app.adapter.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReviewResponse {

    private String id;
    private String customerName;
    private String productId;
    private Double rating;
    private String comment;
    private List<String> pictures;
    private Long createdAt;
    private Long updatedAt;
}
