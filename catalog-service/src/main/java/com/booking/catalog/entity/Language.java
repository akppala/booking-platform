package com.booking.catalog.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("language")
public class Language {

    @Id
    private UUID id;

    private String code;

    private String name;
}