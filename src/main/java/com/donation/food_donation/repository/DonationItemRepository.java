package com.donation.food_donation.repository;

import com.donation.food_donation.model.DonationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DonationItemRepository extends JpaRepository<DonationItem, Long> {

    @Query("SELECT di FROM DonationItem di JOIN FETCH di.user WHERE di.fulfilled = false ORDER BY di.createdAt DESC")
    List<DonationItem> findByFulfilledFalseOrderByCreatedAtDesc();

    @Query("SELECT di FROM DonationItem di JOIN FETCH di.user WHERE di.user.id = :userId AND di.fulfilled = false")
    List<DonationItem> findByUserIdAndFulfilledFalse(@Param("userId") Long userId);

    @Query("SELECT di FROM DonationItem di JOIN FETCH di.user WHERE di.fulfilled = false AND di.updatedAt < :cutoffDate")
    List<DonationItem> findUnfulfilledItemsOlderThan(@Param("cutoffDate") LocalDateTime cutoffDate);
}