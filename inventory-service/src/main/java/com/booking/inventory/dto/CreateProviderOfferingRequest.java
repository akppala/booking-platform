package com.booking.inventory.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProviderOfferingRequest {

    @NotNull
    private UUID providerId;

    @NotNull
    private UUID catalogId;

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private String description;

}
