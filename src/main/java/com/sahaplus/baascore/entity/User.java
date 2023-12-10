package com.sahaplus.baascore.entity;


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
@Entity
@Table(name = "saha_plus_user")
public class User extends BaseClass {
    @NotNull
    private long loginId;

    private String bankOneId;

    private String bankOneCustomerId;

//    @NotNull
    private String firstName;

    private String otherNames;

//    @NotNull
    private String lastName;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

//    @NotNull
    private String mobile_phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDateTime dateOfBirth;
    private String address;
    private CustomerType customerType;

//    @Size(min = 6, max = 6)
    private int pin;
    private String nin;

    private boolean isEmailVerified = false;
    private boolean isPhoneVerified = false;
    private boolean isProfileComplete = false;
    private boolean isBvnVerified = false;
    private boolean isKYCVerified = false;
    private boolean isBlocked = false;
    private boolean isDeleted = false;
    private int loginAttempts = 0;
    private LocalDateTime lastLogin;
    private int pinAttempts = 0;
    private LocalDateTime lastPinAttempt;
    private boolean isPinSet = false;
    private boolean isPinBlocked = false;


    @OneToMany(mappedBy = "accountOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Account> accounts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<KYCDetails> kycDetails;

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
