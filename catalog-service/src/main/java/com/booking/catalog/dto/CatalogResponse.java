package com.booking.catalog.dto;

import java.util.List;
import java.util.UUID;

import com.booking.catalog.entity.Movie.MovieBuilder;
import com.booking.catalog.utils.CatalogStatus;
import com.booking.catalog.utils.CatalogType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class CatalogResponse {

    private UUID id;

    private String code;

    private String name;

    private String description;

    private CatalogType catalogType;

    private CatalogStatus status;

    private List<ReferenceResponse> references;

}
