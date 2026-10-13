package com.booking.inventory.entity;

import java.math.BigDecimal;
import java.util.UUID;

import javax.net.ssl.SSLEngineResult.Status;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("geo_location")
public class GeoLocation extends BaseEntity {

    @Column("parent_id")
    private UUID parentId;

    @Column("geo_type")
    private GeoType geoType;

    @Column("code")
    private String code;

    @Column("name")
    private String name;

    @Column("latitude")
    private BigDecimal latitude;

    @Column("longitude")
    private BigDecimal longitude;

    @Column("search_radius")
    private Double searchRadius;

    @Column("status")
    private Status status;
}
