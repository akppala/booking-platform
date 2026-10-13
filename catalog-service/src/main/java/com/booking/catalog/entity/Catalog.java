package com.booking.catalog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.booking.catalog.utils.CatalogStatus;
import com.booking.catalog.utils.CatalogType;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(schema = "catalog", name = "catalog")
public class Catalog extends VersionEntity {

    @Column("code")
    private String code;

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("catalog_type")
    private CatalogType catalogType;

    @Column("status")
    private CatalogStatus status;

    @Column("external_reference_id")
    private String externalReferenceId;
}