package com.semokin.app.adapter.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public class PageResponse<T> {

    private long totalElements;
    private int totalPages;
    private int currentPage;
    private int numberOfElements;
    private List<T> content;
}
