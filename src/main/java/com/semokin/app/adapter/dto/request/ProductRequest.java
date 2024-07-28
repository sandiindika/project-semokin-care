package com.semokin.app.adapter.dto.request;

import lombok.Data;

@Data
public class ProductRequest {

    private String name;
    private String description;
    private Long price;
    private Integer stock;
    private String categoryId;
    private String brandId;
}
