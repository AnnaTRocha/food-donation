package com.donation.food_donation.dto;

import com.donation.food_donation.model.User.UserType;

public record LoginResponse(
        String token,
        String email,
        String name,
        UserType type
) {
    public static LoginResponse fromUserDetails(String token, String email, String name, UserType type) {
        return new LoginResponse(token, email, name, type);
    }
}