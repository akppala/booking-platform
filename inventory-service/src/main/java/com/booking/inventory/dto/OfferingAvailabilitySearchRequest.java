package com.booking.inventory.dto;

import java.util.UUID;

import com.booking.inventory.service.impl.RecordStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferingAvailabilitySearchRequest {

    private UUID providerOfferingId;

    private UUID availabilityTypeId;

    private UUID parentAvailabilityId;

    private UUID geoLocationId;

    private String code;

    private String name;

    private RecordStatus status;

    @Builder.Default
    private Integer page = 0;

    @Builder.Default
    private Integer size = 20;
}
