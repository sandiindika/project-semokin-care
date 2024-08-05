package com.semokin.app.adapter.mapper;

import com.semokin.app.adapter.dto.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageMapper{

    public <T> PageResponse<T> toResponse(Page<?> page, List<T> content) {
        return PageResponse.<T>builder()
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .currentPage(page.getNumber() + 1)
                .numberOfElements(page.getSize())
                .content(content)
                .build();
    }
}
