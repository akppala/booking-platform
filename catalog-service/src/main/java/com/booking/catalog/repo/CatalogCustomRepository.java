package com.booking.catalog.repo;

import com.booking.catalog.dto.CatalogSearchCriteria;
import com.booking.catalog.entity.Catalog;
import com.booking.common.repo.BaseSearchRepository;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;

public interface CatalogCustomRepository extends BaseSearchRepository<Catalog> {

   Flux<Catalog> search(CatalogSearchCriteria criteria, Pageable pageable);

}