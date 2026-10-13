package com.booking.catalog.entity;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("bus_route")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BusRoute extends BaseEntity {

    //@Id
    //@Column("catalog_id")
    //private UUID catalogId;

    @Column("route_code")
    private String routeCode;

    @Column("operator_name")
    private String operatorName;

    @Column("source")
    private String source;

    @Column("destination")
    private String destination;

    @Column("distance_km")
    private BigDecimal distanceKm;

    @Column("estimated_duration")
    private Integer estimatedDuration;
}