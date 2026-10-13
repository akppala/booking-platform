package com.booking.common.repo;

import com.booking.common.search.QueryBuilder;
import com.booking.common.search.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public abstract class BaseSearchRepositoryImpl<T>
        implements BaseSearchRepository<T> {

    protected final R2dbcEntityTemplate template;

    private final Class<T> entityClass;

    @Override
    public Flux<T> search(SearchRequest request) {

        Criteria criteria = QueryBuilder.build(request);

        Query query = Query.query(criteria);

        if (request.getPageable() != null) {
            query = query.with(request.getPageable());
        }

        return template.select(query, entityClass);
    }
}
