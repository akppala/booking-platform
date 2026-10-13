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
@Table("genre")
public class Genre {

    @Id
    private UUID id;

    private String code;

    private String name;
}