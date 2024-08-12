package com.semokin.app.adapter.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductPictureResponse {

    private String productId;
    private String id;
    private String name;
    private Long size;
    private String url;
}
