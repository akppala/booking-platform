package com.booking.inventory.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.booking.inventory.util.ScheduleStatus;

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
@Table("schedule")
public class Schedule extends BaseEntity {

    @Column("provider_offering_id")
    private UUID providerOfferingId;

    @Column("offering_availability_id")
    private UUID offeringAvailabilityId;

    @Column("schedule_code")
    private String scheduleCode;

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("start_time")
    private LocalDateTime startTime;

    @Column("end_time")
    private LocalDateTime endTime;

    @Column("capacity")
    private Integer capacity;

    @Column("available_capacity")
    private Integer availableCapacity;

    @Column("status")
    private ScheduleStatus status;
}