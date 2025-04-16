package com.donation.food_donation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
//@ToString(exclude = {"items", "donationsMade", "donationsReceived"})
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    private UserType type;

    private String organizationName;

    public enum UserType {
        NGO, CHURCH, SOCIAL_CLUB, INDIVIDUAL
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<DonationItem> items = new ArrayList<>();

    @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Donation> donationsMade = new ArrayList<>();

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Donation> donationsReceived = new ArrayList<>();
}