package com.donation.food_donation.controller;

import com.donation.food_donation.dto.DonationItemRequest;
import com.donation.food_donation.dto.DonationItemResponse;
import com.donation.food_donation.service.DonationItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class DonationItemController {
    private final DonationItemService donationItemService;

    @PostMapping
    public ResponseEntity<DonationItemResponse> createItem(@RequestBody DonationItemRequest request) {
        return ResponseEntity.ok(donationItemService.createItem(request));
    }

    @GetMapping
    public ResponseEntity<List<DonationItemResponse>> getAllActiveItems() {
        return ResponseEntity.ok(donationItemService.getAllActiveItems());
    }

    @GetMapping("/my-items")
    public ResponseEntity<List<DonationItemResponse>> getUserItems() {
        return ResponseEntity.ok(donationItemService.getUserItems());
    }

    @PutMapping("/{id}/fulfill")
    public ResponseEntity<Void> markItemAsFulfilled(@PathVariable Long id) {
        donationItemService.markItemAsFulfilled(id);
        return ResponseEntity.noContent().build();
    }
}