package com.donation.food_donation.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
public class DonationRequest {
    private Long itemId;
    private LocalDateTime scheduledPickupDate;
    private String pickupAddress;

}