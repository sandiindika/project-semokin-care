package com.semokin.app.adapter.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductAvailabilityResponse {

    private String id;
    private String name;
    private Integer stock;
    private String categoryName;
    private String brandName;
}
