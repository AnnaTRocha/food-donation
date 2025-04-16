package com.donation.food_donation.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}