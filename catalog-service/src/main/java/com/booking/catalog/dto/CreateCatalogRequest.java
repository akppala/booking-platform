package com.booking.catalog.dto;

import java.util.ArrayList;
import java.util.List;

import com.booking.catalog.utils.CatalogType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "catalogType",
    visible = true
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = CreateMovieRequest.class, name = "MOVIE")
})
public abstract class CreateCatalogRequest {

    @NotBlank(message = "Code is required")
    private String code;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotNull(message = "Catalog type is required")
    private CatalogType catalogType;

    @Builder.Default
    @Valid
    private List<ReferenceRequest> references = new ArrayList<>();

}
