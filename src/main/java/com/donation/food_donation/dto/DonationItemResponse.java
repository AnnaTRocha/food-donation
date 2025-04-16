package com.donation.food_donation.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DonationItemResponse {
    private Long id;
    private String title;
    private String description;
    private boolean fulfilled;
    private LocalDateTime createdAt;
    private String requesterName;
    private String requesterOrganization;
}