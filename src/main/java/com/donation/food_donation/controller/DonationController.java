package com.donation.food_donation.controller;

import com.donation.food_donation.dto.DonationRequest;
import com.donation.food_donation.dto.DonationResponse;
import com.donation.food_donation.model.Donation;
import com.donation.food_donation.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
@RequiredArgsConstructor
public class DonationController {
    private final DonationService donationService;

    @PostMapping
    public ResponseEntity<DonationResponse> createDonation(@RequestBody DonationRequest request) {
        return ResponseEntity.ok(donationService.createDonation(request));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DonationResponse> updateDonationStatus(
            @PathVariable Long id,
            @RequestParam Donation.DonationStatus status) {
        return ResponseEntity.ok(donationService.updateDonationStatus(id, status));
    }

    @GetMapping("/my-donations")
    public ResponseEntity<List<DonationResponse>> getMyDonations() {
        return ResponseEntity.ok(donationService.getDonationsByDonor());
    }

    @GetMapping("/received-donations")
    public ResponseEntity<List<DonationResponse>> getReceivedDonations() {
        return ResponseEntity.ok(donationService.getDonationsByRecipient());
    }
}