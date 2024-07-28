package com.semokin.app.adapter.dto.response;

import lombok.Builder;

@Builder
public class PageResponse<T> {

    private long totalElements;
    private int totalPages;
    private int currentPage;
    private int numberOfElements;
    private T content;
}
