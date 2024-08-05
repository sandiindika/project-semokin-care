package com.semokin.app.application.service;

import com.semokin.app.adapter.dto.request.ProductRequest;
import com.semokin.app.adapter.dto.response.PageResponse;
import com.semokin.app.adapter.dto.response.ProductResponse;
import com.semokin.app.adapter.mapper.PageMapper;
import com.semokin.app.adapter.mapper.ProductMapper;
import com.semokin.app.application.contract.ProductService;
import com.semokin.app.domain.exception.ResourceNoContentException;
import com.semokin.app.domain.model.Product;
import com.semokin.app.domain.model.ProductPicture;
import com.semokin.app.domain.model.Review;
import com.semokin.app.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final PageMapper pageMapper;

    @Transactional(readOnly = true)
    @Override
    public PageResponse<ProductResponse> findAll(Pageable pageable) {
        Page<Product> products = productRepository.findByIsDeletedFalse(pageable);
        if (products.isEmpty()) throw new ResourceNoContentException("There are no products");

        List<ProductResponse> productResponses = products.getContent().stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());

        return pageMapper.toResponse(products, productResponses);
    }

    @Override
    public ProductResponse findById(String id) {
        return null;
    }

    @Override
    public Page<ProductResponse> findByName(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Page<ProductResponse> findByCategory(String category, Pageable pageable) {
        return null;
    }

    @Override
    public Page<ProductResponse> findByBrand(String brand, Pageable pageable) {
        return null;
    }

    @Override
    public Page<ProductResponse> findByMinPriceAndMaxPrice(Long minPrice, Long maxPrice, Pageable pageable) {
        return null;
    }

    @Override
    public ProductResponse getProductAvailability(String id) {
        return null;
    }

    @Override
    public Page<Review> getProductReviews(String id, Pageable pageable) {
        return null;
    }

    @Override
    public List<ProductPicture> getProductPictures(String id) {
        return List.of();
    }

    @Override
    public ProductResponse save(ProductRequest request) {
        return null;
    }

    @Override
    public ProductResponse updateExisting(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
