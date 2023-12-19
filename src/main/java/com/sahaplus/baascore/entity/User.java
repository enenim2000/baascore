package com.sahaplus.baascore.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sahaplus.baascore.bankone_apis.enums.CustomerType;
import com.sahaplus.baascore.bankone_apis.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "saha_plus_user")
public class User extends BaseClass {
    @NotNull
    private String loginId;

    private String bankOneId;

    private String bankOneCustomerId;

    private String firstName;

    private String otherNames;

    private String lastName;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    private String mobile_phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String dateOfBirth;
    private String placeOfBirth;
    private String streetNo;
    private String StreetName;
    private String city;
    private String state;
    private String country;
    private String address;
    private CustomerType customerType;

    private int pin;
    private String nin;

    private boolean isProfileComplete = false;
    private boolean isBvnVerified = false;
    private boolean isKYCVerified = false;
    private int loginAttempts = 0;
    private LocalDateTime lastLogin;
    private int pinAttempts = 0;
    private LocalDateTime lastPinAttempt;
    private boolean isPinSet = false;
    private boolean isPinBlocked = false;


    @OneToMany(mappedBy = "accountOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Account> accounts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Beneficiary> beneficiaries;
//    private List<Transaction> transactions;
//    private List<Loan> loans;
//    private List<Bill> bills;
//    private List<Notification> notifications;
//    private List<Complaint> complaints;
//    private List<Feedback> feedbacks;

}
