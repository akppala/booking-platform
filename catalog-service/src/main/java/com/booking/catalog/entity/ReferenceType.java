package com.booking.catalog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(schema = "catalog", name = "reference_type")
public class ReferenceType extends BaseEntity {

    private String code;

    private String name;

    private String description;
}