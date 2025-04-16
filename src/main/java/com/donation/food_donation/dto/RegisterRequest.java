package com.donation.food_donation.dto;

import com.donation.food_donation.model.User.UserType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private UserType type;
    private String organizationName;
}