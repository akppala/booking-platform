package com.booking.catalog.service.mapper;

import com.booking.catalog.dto.MovieResponse;
import com.booking.catalog.dto.ReferenceResponse;
import com.booking.catalog.entity.Catalog;
import com.booking.catalog.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieResponseAssembler
        extends CatalogResponseAssembler {

    public MovieResponse assemble(
            Catalog catalog,
            Movie movie,
            List<ReferenceResponse> references) {

        MovieResponse response = MovieResponse.builder()

                //.duration(movie.getDuration())
                .releaseDate(movie.getReleaseDate())
                //.language(movie.getLanguage())
                //.certificate(movie.getCertificate())
                //.synopsis(movie.getSynopsis())

                .build();

        populateCatalog(catalog, response);

        response.setReferences(references);

        return response;
    }

}
