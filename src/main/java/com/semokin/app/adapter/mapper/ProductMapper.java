package com.semokin.app.adapter.mapper;

import com.semokin.app.adapter.dto.request.ProductRequest;
import com.semokin.app.adapter.dto.response.ProductResponse;
import com.semokin.app.domain.exception.ResourceNoContentException;
import com.semokin.app.domain.model.Brand;
import com.semokin.app.domain.model.Category;
import com.semokin.app.domain.model.Product;
import com.semokin.app.infrastructure.repository.BrandRepository;
import com.semokin.app.infrastructure.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    public ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .categoryName(product.getCategory().getName())
                .brandName(product.getBrand().getName())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public Product toModel(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() ->
                new ResourceNoContentException("Category not found"));
        Brand brand = brandRepository.findById(request.getBrandId()).orElseThrow(() ->
                new ResourceNoContentException("Brand not found"));

        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .category(category)
                .brand(brand)
                .createdAt(Instant.now().toEpochMilli())
                .build();
    }
}
