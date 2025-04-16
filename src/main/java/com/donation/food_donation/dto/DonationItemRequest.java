package com.donation.food_donation.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DonationItemRequest {
    private String title;
    private String description;
}