package com.donation.food_donation.dto;

import com.donation.food_donation.model.Donation;
import com.donation.food_donation.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DonationResponse {
    private Long id;
    private DonationItemResponse item;
    private UserResponse donor;
    private UserResponse recipient;
    private LocalDateTime donationDate;
    private LocalDateTime scheduledPickupDate;
    private String pickupAddress;
    private Donation.DonationStatus status;
}