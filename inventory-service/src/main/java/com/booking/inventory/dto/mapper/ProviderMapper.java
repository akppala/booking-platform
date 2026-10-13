package com.booking.inventory.dto.mapper;

import org.springframework.stereotype.Component;

import com.booking.inventory.dto.CreateProviderRequest;
import com.booking.inventory.dto.ProviderResponse;
import com.booking.inventory.entity.Provider;

@Component
public class ProviderMapper {

    public Provider toEntity(CreateProviderRequest request) {

        return Provider.builder()

                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public ProviderResponse toResponse(Provider provider) {

        return ProviderResponse.builder()

                .id(provider.getId())
                .code(provider.getCode())
                .name(provider.getName())
                .description(provider.getDescription())
                .status(provider.getStatus())
                .createdAt(provider.getCreatedAt())
                .updatedAt(provider.getUpdatedAt())

                .build();
    }

}
