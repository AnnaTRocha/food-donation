package com.donation.food_donation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
public class Donation {
    public enum DonationStatus {
        PENDING, SCHEDULED, COMPLETED, CANCELLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private DonationItem item;

    private LocalDateTime donationDate;
    private LocalDateTime scheduledPickupDate;
    private String pickupAddress;

    @Enumerated(EnumType.STRING)
    private DonationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donor_id")
    @JsonIgnoreProperties("donationsMade") // Evita referência circular
    private User donor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id")
    @JsonIgnoreProperties("donationsReceived")
    private User recipient;
}