package com.semokin.app.application.contract;

import com.semokin.app.adapter.dto.request.ProductRequest;
import com.semokin.app.adapter.dto.response.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    PageResponse<ProductResponse> findAllProduct(Pageable pageable); // todo: GET /products
    ProductResponse findProductById(String id); // todo: GET /products/{id}
    PageResponse<ProductResponse> findProductByName(String name, Pageable pageable); // todo: GET /products/search?name={name}
    PageResponse<ProductResponse> findProductByCategory(String category, Pageable pageable); // todo: GET /products/category/{categoryId}
    PageResponse<ProductResponse> findProductByBrand(String brand, Pageable pageable); // todo: GET /products/brand/{brandId}
    PageResponse<ProductResponse> findProductByMinPriceAndMaxPrice(Long minPrice, Long maxPrice, Pageable pageable); // todo: GET /products/price?min={minPrice}&max={maxPrice}

    ProductAvailabilityResponse getProductAvailability(String id); // todo: GET /products/{id}/availability

    PageResponse<ReviewResponse> getProductReviews(String id, Pageable pageable); // todo: GET /products/{id}/reviews

    List<ProductPictureResponse> getProductPictures(String id); // todo: GET /products/{id}/images

    ProductResponse saveProduct(ProductRequest request); // todo: POST /products

    ProductResponse updateExistingProduct(String id, ProductRequest request); // todo: PUT /products/{id}

    void deleteProduct(String id); // todo: DELETE /products/{id}
}
