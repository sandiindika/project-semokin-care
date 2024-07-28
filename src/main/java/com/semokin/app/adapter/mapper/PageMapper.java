package com.semokin.app.adapter.mapper;

import com.semokin.app.adapter.dto.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageMapper<T>{

    public PageResponse toResponse(Page page, T content) {
        return PageResponse.builder()
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .currentPage(page.getNumber() + 1)
                .numberOfElements(page.getSize())
                .content(content)
                .build();
    }
}
