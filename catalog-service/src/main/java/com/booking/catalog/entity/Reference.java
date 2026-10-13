package com.booking.catalog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.booking.catalog.utils.ReferenceStatus;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(schema = "catalog", name = "reference")
public class Reference extends BaseEntity {

    @Column("reference_type_id")
    private UUID referenceTypeId;

    private String code;

    private String name;

    @Column("display_order")
    private Integer displayOrder;

    private ReferenceStatus status;
}