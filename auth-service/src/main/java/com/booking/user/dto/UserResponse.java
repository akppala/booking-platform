package com.booking.user.dto;

import java.math.BigDecimal;

public record UserResponse(

        String id,

        String email,

        String displayName,

        String role,

        String membershipTier,

        BigDecimal walletBalance

) {
}