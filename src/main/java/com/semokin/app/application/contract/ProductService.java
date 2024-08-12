package com.semokin.app.application.contract;

import com.semokin.app.adapter.dto.request.ProductRequest;
import com.semokin.app.adapter.dto.response.*;
import com.semokin.app.domain.model.ProductPicture;
import com.semokin.app.domain.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    PageResponse<ProductResponse> findAll(Pageable pageable); // todo: GET /products
    ProductResponse findById(String id); // todo: GET /products/{id}
    PageResponse<ProductResponse> findByName(String name, Pageable pageable); // todo: GET /products/search?name={name}
    PageResponse<ProductResponse> findByCategory(String category, Pageable pageable); // todo: GET /products/category/{categoryId}
    PageResponse<ProductResponse> findByBrand(String brand, Pageable pageable); // todo: GET /products/brand/{brandId}
    PageResponse<ProductResponse> findByMinPriceAndMaxPrice(Long minPrice, Long maxPrice, Pageable pageable); // todo: GET /products/price?min={minPrice}&max={maxPrice}

    ProductAvailabilityResponse getProductAvailability(String id); // todo: GET /products/{id}/availability

    PageResponse<ReviewResponse> getProductReviews(String id, Pageable pageable); // todo: GET /products/{id}/reviews

    List<ProductPictureResponse> getProductPictures(String id); // todo: GET /products/{id}/images

    ProductResponse save(ProductRequest request); // todo: POST /products

    ProductResponse updateExisting(String id); // todo: PUT /products/{id}

    void delete(String id); // todo: DELETE /products/{id}
}
