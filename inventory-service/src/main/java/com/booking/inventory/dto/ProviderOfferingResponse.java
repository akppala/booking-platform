package com.booking.inventory.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

import com.booking.inventory.util.ProviderOfferingStatus;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProviderOfferingResponse {

    private UUID id;

    private UUID providerId;

    private UUID catalogId;

    private String code;

    private String name;

    private String description;

    private ProviderOfferingStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}