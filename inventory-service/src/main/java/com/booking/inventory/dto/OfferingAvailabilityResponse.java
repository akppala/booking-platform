package com.booking.inventory.dto;

import java.util.UUID;

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
public class OfferingAvailabilityResponse {

    private UUID id;

    private UUID providerOfferingId;

    private UUID availabilityTypeId;

    private UUID parentAvailabilityId;

    private UUID geoLocationId;

    private String code;

    private String name;

    private String description;

    private Status status;

}
