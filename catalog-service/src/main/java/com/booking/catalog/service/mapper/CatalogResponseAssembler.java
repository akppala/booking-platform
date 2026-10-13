package com.booking.catalog.service.mapper;

import com.booking.catalog.dto.CatalogResponse;
import com.booking.catalog.entity.Catalog;
import org.springframework.stereotype.Component;

@Component
public class CatalogResponseAssembler {

    protected void populateCatalog(Catalog catalog,
                                   CatalogResponse response) {

        response.setId(catalog.getId());
        response.setCode(catalog.getCode());
        response.setName(catalog.getName());
        response.setDescription(catalog.getDescription());
        response.setCatalogType(catalog.getCatalogType());
        response.setStatus(catalog.getStatus());
    }

}