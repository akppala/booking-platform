package com.booking.common.repo;

import com.booking.common.search.SearchRequest;
import reactor.core.publisher.Flux;

public interface BaseSearchRepository<T> {

    Flux<T> search(SearchRequest request);

}