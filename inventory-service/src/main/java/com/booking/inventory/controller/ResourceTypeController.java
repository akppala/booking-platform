package com.booking.inventory.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.inventory.service.ResourceTypeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/resource-types")
public class ResourceTypeController {

    private final ResourceTypeService resourceTypeService;

}
