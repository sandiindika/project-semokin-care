package com.semokin.app.application.contract;

import com.semokin.app.domain.model.Product;
import com.semokin.app.domain.model.ProductPicture;
import com.semokin.app.domain.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<Product> findAll(Pageable pageable); // todo: GET /products
    Product findById(String id); // todo: GET /products/{id}
    Page<Product> findByName(String name, Pageable pageable); // todo: GET /products/search?name={name}
    Page<Product> findByCategory(String category, Pageable pageable); // todo: GET /products/category/{categoryId}
    Page<Product> findByBrand(String brand, Pageable pageable); // todo: GET /products/brand/{brandId}
    Page<Product> findByMinPriceAndMaxPrice(Long minPrice, Long maxPrice, Pageable pageable); // todo: GET /products/price?min={minPrice}&max={maxPrice}

    Product getProductAvailability(String id); // todo: GET /products/{id}/availability

    Page<Review> getProductReviews(String id, Pageable pageable); // todo: GET /products/{id}/reviews

    List<ProductPicture> getProductPictures(String id); // todo: GET /products/{id}/images

    Product save(Product product); // todo: POST /products

    Product updateExisting(String id); // todo: PUT /products/{id}

    void delete(String id); // todo: DELETE /products/{id}
}
