package com.semokin.app.application.service;

import com.semokin.app.adapter.dto.request.ProductRequest;
import com.semokin.app.adapter.dto.response.*;
import com.semokin.app.adapter.mapper.*;
import com.semokin.app.application.contract.ProductService;
import com.semokin.app.domain.exception.ResourceNoContentException;
import com.semokin.app.domain.model.Product;
import com.semokin.app.domain.model.ProductPicture;
import com.semokin.app.domain.model.Review;
import com.semokin.app.infrastructure.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final PageMapper pageMapper;
    private final ProductAvailabilityMapper productAvailabilityMapper;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final ProductPictureRepository productPictureRepository;
    private final ProductPictureMapper productPictureMapper;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

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

    @Transactional(readOnly = true)
    @Override
    public ProductResponse findById(String id) {
        Optional<Product> product = productRepository.findById(id);

        return product.map(productMapper::toResponse).orElseThrow(() ->
                new ResourceNoContentException("Product not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<ProductResponse> findByName(String name, Pageable pageable) {
        Page<Product> products = productRepository.findByNameContainingIgnoreCase(name, pageable);
        if (products.isEmpty()) throw new ResourceNoContentException("There are no products");

        List<ProductResponse> productResponses = products.getContent().stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());

        return pageMapper.toResponse(products, productResponses);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<ProductResponse> findByCategory(String category, Pageable pageable) {
        categoryRepository.findById(category).orElseThrow(() -> new ResourceNoContentException("Category not found"));

        Page<Product> products = productRepository.findByCategoryId(category, pageable);
        if (products.isEmpty()) throw new ResourceNoContentException("There are no products");

        List<ProductResponse> productResponses = products.getContent().stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());

        return pageMapper.toResponse(products, productResponses);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<ProductResponse> findByBrand(String brand, Pageable pageable) {
        brandRepository.findById(brand).orElseThrow(() -> new ResourceNoContentException("Brand not found"));

        Page<Product> products = productRepository.findByBrandId(brand, pageable);
        if (products.isEmpty()) throw new ResourceNoContentException("There are no products");

        List<ProductResponse> productResponses = products.getContent().stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());

        return pageMapper.toResponse(products, productResponses);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<ProductResponse> findByMinPriceAndMaxPrice(Long minPrice, Long maxPrice, Pageable pageable) {
        Page<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
        if (products.isEmpty()) throw new ResourceNoContentException("There are no products");

        List<ProductResponse> productResponses = products.getContent().stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());

        return pageMapper.toResponse(products, productResponses);
    }

    @Transactional(readOnly = true)
    @Override
    public ProductAvailabilityResponse getProductAvailability(String id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNoContentException("Product not found"));
        return productAvailabilityMapper.toResponse(product);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<ReviewResponse> getProductReviews(String id, Pageable pageable) {
        if (!productRepository.existsById(id)) throw new ResourceNoContentException("Product not found");

        Page<Review> reviews = reviewRepository.findByProductId(id, pageable);
        if (reviews.isEmpty()) throw new ResourceNoContentException("There are no reviews for this product");

        List<ReviewResponse> reviewResponses = reviews.getContent().stream()
                .map(reviewMapper::toResponse)
                .collect(Collectors.toList());

        return pageMapper.toResponse(reviews, reviewResponses);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductPictureResponse> getProductPictures(String id) {
        if (!productRepository.existsById(id)) throw new ResourceNoContentException("Product not found");

        List<ProductPicture> pictures = productPictureRepository.findByProductId(id);
        if (pictures.isEmpty()) throw new ResourceNoContentException("There are no picture for this product");

        return pictures.stream()
                .map(productPictureMapper::toResponse)
                .collect(Collectors.toList());
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
