package com.booking.catalog.dto;

import com.booking.catalog.utils.CatalogStatus;
import com.booking.catalog.utils.CatalogType;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CatalogSearchCriteria {

    String keyword;

    CatalogType catalogType;

    CatalogStatus status;

    String referenceType;

    String referenceCode;
}
