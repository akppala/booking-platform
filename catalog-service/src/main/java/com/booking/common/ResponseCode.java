package com.booking.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    SUCCESS("SUCCESS", "Request processed successfully"),

    CATALOG_CREATED("CATALOG_201", "Catalog created successfully"),

    CATALOG_UPDATED("CATALOG_200", "Catalog updated successfully"),

    CATALOG_DELETED("CATALOG_204", "Catalog deleted successfully"),

    CATALOG_NOT_FOUND("CATALOG_404", "Catalog not found"),

    VALIDATION_ERROR("COMMON_400", "Validation failed"),

    INTERNAL_SERVER_ERROR("COMMON_500", "Internal server error");

    private final String code;

    private final String message;
}
