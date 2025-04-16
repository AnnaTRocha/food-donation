package com.donation.food_donation.repository;

import com.donation.food_donation.model.DonationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DonationItemRepository extends JpaRepository<DonationItem, Long> {
    Optional<DonationItem> findById(Long id);
    List<DonationItem> findByFulfilledFalseOrderByCreatedAtDesc();
    List<DonationItem> findByUserIdAndFulfilledFalse(Long userId);

    @Query("SELECT di FROM DonationItem di WHERE di.fulfilled = false AND di.updatedAt < :cutoffDate")
    List<DonationItem> findUnfulfilledItemsOlderThan(LocalDateTime cutoffDate);
}