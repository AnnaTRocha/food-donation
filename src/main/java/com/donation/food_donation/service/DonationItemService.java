package com.donation.food_donation.service;

import com.donation.food_donation.dto.DonationItemRequest;
import com.donation.food_donation.dto.DonationItemResponse;
import com.donation.food_donation.exception.ResourceNotFoundException;
import com.donation.food_donation.model.DonationItem;
import com.donation.food_donation.model.User;
import com.donation.food_donation.repository.DonationItemRepository;
import com.donation.food_donation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DonationItemService {
    private final DonationItemRepository donationItemRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public DonationItemResponse createItem(DonationItemRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        DonationItem item = new DonationItem();
        item.setTitle(request.getTitle());
        item.setDescription(request.getDescription());
        item.setUser(user);

        DonationItem savedItem = donationItemRepository.save(item);

        return mapToResponse(savedItem);
    }

    public List<DonationItemResponse> getAllActiveItems() {
        return donationItemRepository.findByFulfilledFalseOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<DonationItemResponse> getUserItems() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return donationItemRepository.findByUserIdAndFulfilledFalse(user.getId())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public void markItemAsFulfilled(Long itemId) {
        DonationItem item = donationItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

        item.setFulfilled(true);
        item.setUpdatedAt(LocalDateTime.now());
        donationItemRepository.save(item);
    }

    public void checkAndNotifyUnfulfilledItems() {
        LocalDateTime cutoffDate = LocalDateTime.now().minusWeeks(1);
        List<DonationItem> items = donationItemRepository.findUnfulfilledItemsOlderThan(cutoffDate);

        items.forEach(item -> {
            emailService.sendItemReminderEmail(item.getUser().getEmail(), item);
            item.setUpdatedAt(LocalDateTime.now());
            donationItemRepository.save(item);
        });
    }

    private DonationItemResponse mapToResponse(DonationItem item) {
        DonationItemResponse response = new DonationItemResponse();
        response.setId(item.getId());
        response.setTitle(item.getTitle());
        response.setDescription(item.getDescription());
        response.setFulfilled(item.isFulfilled());
        response.setCreatedAt(item.getCreatedAt());
        response.setRequesterName(item.getUser().getName());
        response.setRequesterOrganization(item.getUser().getOrganizationName());
        return response;
    }
}