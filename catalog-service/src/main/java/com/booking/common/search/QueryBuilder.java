package com.booking.common.search;

import org.springframework.data.relational.core.query.Criteria;

public final class QueryBuilder {

    private QueryBuilder() {
    }

    public static Criteria build(SearchRequest request) {

        Criteria criteria = Criteria.empty();

        if (request == null || request.getFilters().isEmpty()) {
            return criteria;
        }

        for (SearchFilter filter : request.getFilters()) {

            Criteria current = buildCriteria(filter);

            criteria = criteria.and(current);
        }

        return criteria;
    }

    private static Criteria buildCriteria(SearchFilter filter) {

        return switch (filter.getOperator()) {

            case EQUALS ->
                    Criteria.where(filter.getField())
                            .is(filter.getValue());

            case NOT_EQUALS ->
                    Criteria.where(filter.getField())
                            .not(filter.getValue());

            case LIKE ->
                    Criteria.where(filter.getField())
                            .like("%" + filter.getValue() + "%");

            case STARTS_WITH ->
                    Criteria.where(filter.getField())
                            .like(filter.getValue() + "%");

            case ENDS_WITH ->
                    Criteria.where(filter.getField())
                            .like("%" + filter.getValue());

            case GREATER_THAN ->
                    Criteria.where(filter.getField())
                            .greaterThan(filter.getValue());

            case GREATER_THAN_EQUAL ->
                    Criteria.where(filter.getField())
                            .greaterThanOrEquals(filter.getValue());

            case LESS_THAN ->
                    Criteria.where(filter.getField())
                            .lessThan(filter.getValue());

            case LESS_THAN_EQUAL ->
                    Criteria.where(filter.getField())
                            .lessThanOrEquals(filter.getValue());

            case IS_NULL ->
                    Criteria.where(filter.getField())
                            .isNull();

            case IS_NOT_NULL ->
                    Criteria.where(filter.getField())
                            .isNotNull();

            default ->
                    throw new UnsupportedOperationException(
                            "Operator not supported: " + filter.getOperator());
        };
    }
}