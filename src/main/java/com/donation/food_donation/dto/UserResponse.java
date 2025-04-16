package com.donation.food_donation.dto;

import com.donation.food_donation.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private User.UserType type;
    private String phone;
    private String address;
    private String organizationName;
}