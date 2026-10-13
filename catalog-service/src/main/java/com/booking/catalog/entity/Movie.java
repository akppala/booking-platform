package com.booking.catalog.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie",schema = "catalog")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Movie extends VersionEntity {

    @Column("catalog_id")
    private UUID catalogId;

    @Column("duration_minutes")
    private Integer durationMinutes;

    @Column("release_date")
    private LocalDate releaseDate;

    @Column("certificate")
    private String certificate;

   /* @Column("language")
    private String language;

    @Column("director")
    private String director;

    @Column("production_house")
    private String productionHouse;

    @Column("imdb_rating")
    private BigDecimal imdbRating;

    @Column("budget")
    private BigDecimal budget;

    @Column("box_office")
    private BigDecimal boxOffice;*/

    @Column("trailer_url")
    private String trailerUrl;
    @Column("version")
    private Long version;
}
