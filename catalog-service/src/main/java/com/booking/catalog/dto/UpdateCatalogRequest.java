package com.booking.catalog.dto;

import com.booking.catalog.utils.CatalogStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCatalogRequest {

    @NotBlank(message = "Catalog name is required")
    @Size(max = 200)
    private String name;

    @Size(max = 1000)
    private String description;

    private CatalogStatus status;

    private String referenceId;
}

