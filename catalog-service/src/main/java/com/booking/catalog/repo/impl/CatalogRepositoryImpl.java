package com.booking.catalog.repo.impl;

import com.booking.catalog.dto.CatalogSearchCriteria;
import com.booking.catalog.entity.Catalog;
import com.booking.catalog.repo.CatalogCustomRepository;
import com.booking.common.repo.BaseSearchRepositoryImpl;

import reactor.core.publisher.Flux;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CatalogRepositoryImpl
        extends BaseSearchRepositoryImpl<Catalog>
        implements CatalogCustomRepository {

    public CatalogRepositoryImpl(R2dbcEntityTemplate template) {
        super(template, Catalog.class);
    }

    @Override
    public Flux<Catalog> search(CatalogSearchCriteria criteria, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

}