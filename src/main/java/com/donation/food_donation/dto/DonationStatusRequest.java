package com.donation.food_donation.dto;

import com.donation.food_donation.model.Donation;
import lombok.Data;

@Data
public class DonationStatusRequest {
    private Donation.DonationStatus status;
}