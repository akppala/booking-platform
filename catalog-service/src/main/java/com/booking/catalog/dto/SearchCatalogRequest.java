package com.booking.catalog.dto;

import com.booking.catalog.utils.CatalogStatus;
import com.booking.catalog.utils.CatalogType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchCatalogRequest {

    private String keyword;

    private CatalogType catalogType;

    private CatalogStatus catalogStatus;

    @Builder.Default
    private Integer page = 0;

    @Builder.Default
    private Integer size = 20;

    @Builder.Default
    private String sortBy = "createdAt";

    @Builder.Default
    private String sortDirection = "DESC";
}
