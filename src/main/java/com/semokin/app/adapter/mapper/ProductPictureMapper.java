package com.semokin.app.adapter.mapper;

import com.semokin.app.adapter.dto.response.ProductPictureResponse;
import com.semokin.app.domain.model.ProductPicture;
import org.springframework.stereotype.Component;

@Component
public class ProductPictureMapper {

    public ProductPictureResponse toResponse(ProductPicture productPicture) {
        return ProductPictureResponse.builder()
                .productId(productPicture.getProduct().getId())
                .id(productPicture.getId())
                .name(productPicture.getName())
                .size(productPicture.getSize())
                .url(productPicture.getUrl())
                .build();
    }
}
