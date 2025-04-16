package com.donation.food_donation.dto;

import com.donation.food_donation.model.User.UserType;

public record AuthResponse(
        String token,
        String email,
        String name,
        UserType type
) {}