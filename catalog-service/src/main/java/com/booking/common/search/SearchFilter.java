package com.booking.common.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchFilter {

    /**
     * Database field name.
     *
     * Example:
     * name
     * catalogType
     * status
     */
    private String field;

    /**
     * Comparison operator.
     */
    private SearchOperator operator;

    /**
     * Value used in comparison.
     */
    private Object value;
}