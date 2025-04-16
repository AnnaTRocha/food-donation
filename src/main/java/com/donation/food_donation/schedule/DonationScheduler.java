package com.donation.food_donation.schedule;

import com.donation.food_donation.service.DonationItemService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DonationScheduler {
    private final DonationItemService donationItemService;

    public DonationScheduler(DonationItemService donationItemService) {
        this.donationItemService = donationItemService;
    }

    // Executa toda segunda-feira às 9:00 AM
    @Scheduled(cron = "0 0 9 * * MON")
    public void checkAndNotifyUnfulfilledItems() {
        donationItemService.checkAndNotifyUnfulfilledItems();
    }
}