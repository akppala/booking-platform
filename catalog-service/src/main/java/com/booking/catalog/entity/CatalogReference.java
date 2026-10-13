package com.booking.catalog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(schema = "catalog", name = "catalog_reference")
public class CatalogReference {

    @Column("catalog_id")
    private UUID catalogId;

    @Column("relationship_type")
    private String relationshipType;

    @Column("reference_id")
    private UUID referenceId;
}