package com.booking.catalog.service.handler;

import com.booking.catalog.service.handler.CatalogTypeHandler;
import com.booking.catalog.utils.CatalogType;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class CatalogHandlerFactory {

    private final Map<CatalogType, CatalogTypeHandler> handlers =
            new EnumMap<>(CatalogType.class);

    public CatalogHandlerFactory(List<CatalogTypeHandler> handlerList) {

        handlerList.forEach(handler ->
                handlers.put(handler.getCatalogType(), handler));
    }

    public CatalogTypeHandler getHandler(CatalogType catalogType) {

        CatalogTypeHandler handler = handlers.get(catalogType);

        if (handler == null) {
            throw new IllegalArgumentException(
                    "No handler found for catalog type: " + catalogType);
        }

        return handler;
    }
}
