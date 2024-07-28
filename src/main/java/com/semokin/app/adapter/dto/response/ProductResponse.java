package com.semokin.app.adapter.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ProductResponse {

    private String id;
    private String name;
    private String description;
    private Long price;
    private Integer stock;
    private String categoryName;
    private String brandName;
    private Long createdAt;
    private Long updatedAt;
}
