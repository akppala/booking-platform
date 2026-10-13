package com.booking.catalog.dto;

import com.booking.catalog.utils.RelationshipType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceResponse {

    /**
     * Relationship between Catalog and Reference.
     * Example:
     * PRIMARY_GENRE
     * ORIGINAL_LANGUAGE
     * FORMAT
     */
    private RelationshipType relationshipType;

    /**
     * Reference type.
     * Example:
     * GENRE
     * LANGUAGE
     * FORMAT
     */
    private String referenceType;

    /**
     * Reference code.
     * Example:
     * SCI_FI
     * EN
     * IMAX
     */
    private String referenceCode;

    /**
     * Display name.
     * Example:
     * Science Fiction
     * English
     * IMAX
     */
    private String referenceName;
}
