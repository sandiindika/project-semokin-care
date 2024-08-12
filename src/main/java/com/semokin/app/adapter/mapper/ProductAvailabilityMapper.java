package com.semokin.app.adapter.mapper;

import com.semokin.app.adapter.dto.response.ProductAvailabilityResponse;
import com.semokin.app.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductAvailabilityMapper {

    public ProductAvailabilityResponse toResponse(Product product) {
        return ProductAvailabilityResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .stock(product.getStock())
                .categoryName(product.getCategory().getName())
                .brandName(product.getBrand().getName())
                .build();
    }
}
