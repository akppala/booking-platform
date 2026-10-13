package com.booking.catalog.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMovieRequest extends CreateCatalogRequest {

    @NotNull
    @Positive
    private Integer durationMinutes;

    @NotNull
    private LocalDate releaseDate;

    private String posterUrl;

    private String bannerUrl;

    private String trailerUrl;

}
