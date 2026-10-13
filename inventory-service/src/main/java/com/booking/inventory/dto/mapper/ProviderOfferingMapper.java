package com.booking.inventory.dto.mapper;

import org.springframework.stereotype.Component;

import com.booking.inventory.dto.CreateProviderOfferingRequest;
import com.booking.inventory.dto.ProviderOfferingResponse;
import com.booking.inventory.entity.ProviderOffering;

@Component
public class ProviderOfferingMapper {

    public ProviderOffering toEntity(CreateProviderOfferingRequest request) {

        return ProviderOffering.builder()
                .providerId(request.getProviderId())
                .catalogId(request.getCatalogId())
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public ProviderOfferingResponse toResponse(ProviderOffering entity) {

        return ProviderOfferingResponse.builder()
                .id(entity.getId())
                .providerId(entity.getProviderId())
                .catalogId(entity.getCatalogId())
                .code(entity.getCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
