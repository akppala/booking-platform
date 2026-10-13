package com.booking.inventory.entity;

import java.util.UUID;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.booking.inventory.util.ProviderOfferingStatus;

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
@Table("provider_offering")
public class ProviderOffering extends BaseEntity {

    @Column("provider_id")
    private UUID providerId;

    @Column("catalog_id")
    private UUID catalogId;

    @Column("code")
    private String code;

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("status")
    private ProviderOfferingStatus status;

}