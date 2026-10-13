package com.booking.inventory.entity;

import java.util.UUID;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.booking.inventory.util.Status;

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
@Table("offering_availability")
public class OfferingAvailability extends BaseEntity {

    @Column("provider_offering_id")
    private UUID providerOfferingId;

    @Column("availability_type_id")
    private UUID availabilityTypeId;

    @Column("parent_availability_id")
    private UUID parentAvailabilityId;

    @Column("geo_location_id")
    private UUID geoLocationId;

    @Column("code")
    private String code;

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("status")
    private Status status;

}