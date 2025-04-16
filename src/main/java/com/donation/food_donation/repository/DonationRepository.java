package com.donation.food_donation.repository;

import com.donation.food_donation.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByDonorId(Long donorId);
    List<Donation> findByRecipientId(Long recipientId);
    List<Donation> findByItemId(Long itemId);
}