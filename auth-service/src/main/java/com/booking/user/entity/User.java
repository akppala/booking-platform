package com.booking.user.entity;

import lombok.*;

import org.springframework.data.annotation.Id;

import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Table("users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private UUID id;

    // Identity

    private String oktaUserId;

    private String provider;

    private String providerUserId;

    // Profile

    private String email;

    private Boolean emailVerified;

    private String firstName;

    private String lastName;

    private String displayName;

    private String mobile;

    private String countryCode;

    private LocalDate dateOfBirth;

    private String gender;

    private String profileImageUrl;

    // Platform

    private String role;

    private String status;

    private String membershipTier;

    private BigDecimal walletBalance;

    // Audit

    private Instant createdAt;

    private Instant updatedAt;

    private Instant lastLoginAt;
}