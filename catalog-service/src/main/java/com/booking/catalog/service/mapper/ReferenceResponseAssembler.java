package com.booking.catalog.service.mapper;

import com.booking.catalog.dto.ReferenceResponse;
import com.booking.catalog.entity.Reference;
import org.springframework.stereotype.Component;

@Component
public class ReferenceResponseAssembler {

    public ReferenceResponse assemble(Reference reference) {

        return ReferenceResponse.builder()

                //.id(reference.getId())

                //.code(reference.getCode())

                //.name(reference.getName())

                //.description(reference.getDescription())

                //.status(reference.getStatus())

                .build();

    }

}
