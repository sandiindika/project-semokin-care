package com.semokin.app.application.service;

import com.semokin.app.adapter.dto.response.CategoryResponse;
import com.semokin.app.adapter.mapper.CategoryMapper;
import com.semokin.app.application.contract.CategoryService;
import com.semokin.app.domain.exception.ResourceNoContentException;
import com.semokin.app.domain.model.Category;
import com.semokin.app.infrastructure.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    @Override
    public CategoryResponse findCategoryById(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new ResourceNoContentException("Category not found"));

        return categoryMapper.toResponse(category);
    }
}