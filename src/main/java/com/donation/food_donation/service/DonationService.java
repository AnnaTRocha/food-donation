package com.donation.food_donation.service;

import com.donation.food_donation.model.*;
import com.donation.food_donation.dto.DonationItemResponse;
import com.donation.food_donation.dto.DonationRequest;
import com.donation.food_donation.dto.DonationResponse;
import com.donation.food_donation.dto.UserResponse;
import com.donation.food_donation.exception.ResourceNotFoundException;
import com.donation.food_donation.repository.DonationItemRepository;
import com.donation.food_donation.repository.DonationRepository;
import com.donation.food_donation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DonationService {
    private final DonationRepository donationRepository;
    private final DonationItemRepository donationItemRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public DonationResponse createDonation(DonationRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User donor = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        DonationItem item = donationItemRepository.findById(request.getItemId())
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

        User recipient = item.getUser();

        Donation donation = new Donation();
        donation.setItem(item);
        donation.setDonor(donor);
        donation.setRecipient(recipient);
        donation.setDonationDate(LocalDateTime.now());
        donation.setScheduledPickupDate(request.getScheduledPickupDate());
        donation.setPickupAddress(request.getPickupAddress());
        donation.setStatus(Donation.DonationStatus.PENDING);

        Donation savedDonation = donationRepository.save(donation);

        // Notify recipient
        // emailService.sendDonationNotificationEmail(recipient.getEmail(), savedDonation);

        return mapToResponse(savedDonation);
    }

    public DonationResponse updateDonationStatus(@PathVariable Long donationId, @RequestParam Donation.DonationStatus status) {
        Donation donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new ResourceNotFoundException("Donation not found"));

        donation.setStatus(status);

        if (status == Donation.DonationStatus.COMPLETED) {
            donation.getItem().setFulfilled(true);
            donation.getItem().setUpdatedAt(LocalDateTime.now());
            donationItemRepository.save(donation.getItem());
        }

        Donation updatedDonation = donationRepository.save(donation);
        return mapToResponse(updatedDonation);
    }

    public List<DonationResponse> getDonationsByDonor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User donor = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return donationRepository.findByDonorId(donor.getId())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<DonationResponse> getDonationsByRecipient() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User recipient = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return donationRepository.findByRecipientId(recipient.getId())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public DonationResponse mapToResponse(Donation donation) {
        DonationResponse response = new DonationResponse();
        response.setId(donation.getId());

        // Item information
        response.setItemId(donation.getItem().getId());
        response.setItemTitle(donation.getItem().getTitle());
        response.setItemDescription(donation.getItem().getDescription());

        // Donor information
        response.setDonorId(donation.getDonor().getId());
        response.setDonorName(donation.getDonor().getName());

        // Recipient information
        response.setRecipientId(donation.getRecipient().getId());
        response.setRecipientName(donation.getRecipient().getName());
        response.setRequesterOrganization(donation.getRecipient().getOrganizationName());

        // Donation details
        response.setDonationDate(donation.getDonationDate());
        response.setScheduledPickupDate(donation.getScheduledPickupDate());
        response.setPickupAddress(donation.getPickupAddress());
        response.setStatus(donation.getStatus());

        return response;
    }
}