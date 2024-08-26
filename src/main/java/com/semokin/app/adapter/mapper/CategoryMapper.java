package com.semokin.app.adapter.mapper;

import com.semokin.app.adapter.dto.response.CategoryResponse;
import com.semokin.app.domain.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse (Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
