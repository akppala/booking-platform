package com.booking.catalog.dto;

import java.time.LocalDate;

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
public class MovieResponse extends CatalogResponse {

    private Integer durationMinutes;

    private LocalDate releaseDate;

    private String posterUrl;

    private String bannerUrl;

    private String trailerUrl;

}
