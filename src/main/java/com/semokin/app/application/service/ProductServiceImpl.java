package com.semokin.app.application.service;

import com.semokin.app.adapter.dto.request.ProductRequest;
import com.semokin.app.adapter.dto.response.*;
import com.semokin.app.adapter.mapper.*;
import com.semokin.app.application.contract.ProductService;
import com.semokin.app.domain.exception.ResourceNoContentException;
import com.semokin.app.domain.model.*;
import com.semokin.app.infrastructure.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
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
    public PageResponse<ProductResponse> findAllProduct(Pageable pageable) {
        Page<Product> products = productRepository.findByIsDeletedFalse(pageable);
        if (products.isEmpty()) throw new ResourceNoContentException("There are no products");

        List<ProductResponse> productResponses = products.getContent().stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());

        return pageMapper.toResponse(products, productResponses);
    }

    @Transactional(readOnly = true)
    @Override
    public ProductResponse findProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNoContentException("Product not found"));

        return productMapper.toResponse(product);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<ProductResponse> findProductByName(String name, Pageable pageable) {
        Page<Product> products = productRepository.findByNameContainingIgnoreCase(name, pageable);
        if (products.isEmpty()) throw new ResourceNoContentException("There are no products");

        List<ProductResponse> productResponses = products.getContent().stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());

        return pageMapper.toResponse(products, productResponses);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<ProductResponse> findProductByCategory(String category, Pageable pageable) {
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
    public PageResponse<ProductResponse> findProductByBrand(String brand, Pageable pageable) {
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
    public PageResponse<ProductResponse> findProductByMinPriceAndMaxPrice(Long minPrice, Long maxPrice, Pageable pageable) {
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ProductResponse saveProduct(ProductRequest request) {
        Product product = productMapper.toModel(request);
        productRepository.save(product);

        return productMapper.toResponse(product);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ProductResponse updateExistingProduct(String id, ProductRequest request) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() ->
                new ResourceNoContentException("Product not found"));

        Category existingCategory = categoryRepository.findById(request.getCategoryId()).orElseThrow(() ->
                new ResourceNoContentException("Category not found"));

        Brand existingBrand = brandRepository.findById(request.getBrandId()).orElseThrow(() ->
                new ResourceNoContentException("Brand not found"));

        existingProduct.setName(request.getName());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setStock(request.getStock());
        existingProduct.setCategory(existingCategory);
        existingProduct.setBrand(existingBrand);
        existingProduct.setUpdatedAt(Instant.now().toEpochMilli());

        Product product = productRepository.save(existingProduct);

        return productMapper.toResponse(product);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNoContentException("Product not found"));

        product.setIsDeleted(true);
        productRepository.save(product);
    }
}
