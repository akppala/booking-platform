package com.booking.inventory.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

import com.booking.inventory.service.impl.RecordStatus;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityTypeResponse {

    private UUID id;

    private UUID providerOfferingId;

    private UUID parentTypeId;

    private String code;

    private String name;

    private Integer hierarchyLevel;

    private Boolean bookable;

    private RecordStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}