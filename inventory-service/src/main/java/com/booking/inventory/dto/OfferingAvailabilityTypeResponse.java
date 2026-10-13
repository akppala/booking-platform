package com.booking.inventory.dto;

import java.util.UUID;

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
public class OfferingAvailabilityTypeResponse {

    private UUID id;

    private UUID providerOfferingId;

    private UUID parentTypeId;

    private String code;

    private String name;

    private Integer level;

    private Boolean bookable;

}