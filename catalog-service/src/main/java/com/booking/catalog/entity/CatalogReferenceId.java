package com.booking.catalog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogReferenceId implements Serializable {

    private UUID catalogId;

    private String relationshipType;

    private UUID referenceId;
}