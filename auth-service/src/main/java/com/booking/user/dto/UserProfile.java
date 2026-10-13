package com.booking.user.dto;

import lombok.Data;

@Data
public class UserProfile {

    private String sub;

    private String name;

    private String email;

    private String picture;

    private Boolean emailVerified;

    private String givenName;

    private String familyName;
}
