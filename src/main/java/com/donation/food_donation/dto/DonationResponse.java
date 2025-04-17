package com.donation.food_donation.dto;

import com.donation.food_donation.model.Donation;
import com.donation.food_donation.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DonationResponse {
    private Long id;
    private Long itemId;
    private String itemTitle;
    private String itemDescription;
    private Long donorId;
    private String donorName;
    private Long recipientId;
    private String recipientName;
    private LocalDateTime donationDate;
    private LocalDateTime scheduledPickupDate;
    private String pickupAddress;
    private Donation.DonationStatus status;
    private String requesterOrganization;
}