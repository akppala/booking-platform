package com.booking.inventory.dto.mapper;

import org.springframework.stereotype.Component;

import com.booking.inventory.dto.AvailabilityTypeResponse;
import com.booking.inventory.dto.CreateAvailabilityTypeRequest;
import com.booking.inventory.entity.OfferingAvailabilityType;

@Component
public class AvailabilityTypeMapper {

    public OfferingAvailabilityType toEntity(CreateAvailabilityTypeRequest request) {

        return OfferingAvailabilityType.builder()
                .providerOfferingId(request.getProviderOfferingId())
                .parentTypeId(request.getParentTypeId())
                .code(request.getCode())
                .name(request.getName())
                //.hierarchyLevel(request.getHierarchyLevel())
                .bookable(request.getBookable())
                .build();
    }

    public AvailabilityTypeResponse toResponse(OfferingAvailabilityType entity) {

        return AvailabilityTypeResponse.builder()
                .id(entity.getId())
                .providerOfferingId(entity.getProviderOfferingId())
                .parentTypeId(entity.getParentTypeId())
                .code(entity.getCode())
                .name(entity.getName())
                //.hierarchyLevel(entity.getHierarchyLevel())
                .bookable(entity.getBookable())
                //.status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
