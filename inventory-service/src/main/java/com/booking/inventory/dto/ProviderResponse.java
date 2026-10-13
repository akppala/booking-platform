package com.booking.inventory.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.booking.inventory.util.ProviderStatus;
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
public class ProviderResponse {

    private UUID id;

    private String code;

    private String name;

    private String description;

    private ProviderStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
