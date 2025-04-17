package com.donation.food_donation.repository;

import com.donation.food_donation.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    List<Donation> findByDonorId(Long donorId);

    List<Donation> findByRecipientId(Long recipientId);

    @Query("SELECT d FROM Donation d WHERE d.donor.id = :userId OR d.recipient.id = :userId")
    List<Donation> findAllByUserId(@Param("userId") Long userId);
}