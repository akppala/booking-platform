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
@Table("train_route")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TrainRoute extends BaseEntity {

    //@Id
    //@Column("catalog_id")
    //private UUID catalogId;

    @Column("train_number")
    private String trainNumber;

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