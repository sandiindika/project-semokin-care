package com.semokin.app.application.contract;

import com.semokin.app.adapter.dto.response.CategoryResponse;

public interface CategoryService {

    CategoryResponse findCategoryById(String id); // todo: GET /categories/{id}
}
