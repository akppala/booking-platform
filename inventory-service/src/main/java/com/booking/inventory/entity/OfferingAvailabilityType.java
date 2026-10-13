package com.booking.inventory.entity;

import java.util.UUID;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table("offering_availability_type")
public class OfferingAvailabilityType extends BaseEntity {

    @Column("provider_offering_id")
    private UUID providerOfferingId;

    @Column("parent_type_id")
    private UUID parentTypeId;

    @Column("code")
    private String code;

    @Column("name")
    private String name;

    @Column("level")
    private Integer level;

    @Column("bookable")
    private Boolean bookable;

}