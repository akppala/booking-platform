package com.booking.inventory.dto;

import java.time.LocalDateTime;
import java.util.UUID;

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
public class ScheduleResponse {

    private UUID id;

    private UUID providerOfferingId;

    private UUID offeringAvailabilityId;

    private String scheduleCode;

    private String name;

    private String description;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer capacity;

    private Integer availableCapacity;

    private ScheduleStatus status;
}