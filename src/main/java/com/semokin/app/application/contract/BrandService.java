package com.semokin.app.application.contract;

import com.semokin.app.adapter.dto.response.BrandResponse;

public interface BrandService {

    BrandResponse findBrandById(String id); // todo: GET /brands/{id}
}
