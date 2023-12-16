package com.sahaplus.baascore.dto.request;

import com.sahaplus.baascore.bankone_apis.enums.CustomerType;
import com.sahaplus.baascore.bankone_apis.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
    private String firstName;
    private String otherNames;
    private String lastName;
    private String mobile_phone;
    private String dateOfBirth;
    private String address;
    private CustomerType customerType;
    private int pin;
    private String nin;
    private boolean isEmailVerified = false;
    private boolean isPhoneVerified = false;
    private boolean isProfileComplete = false;
    private boolean isBvnVerified = false;
    private boolean isKYCVerified = false;
    private boolean isBlocked = false;
    private boolean isDeleted = false;
    private boolean isPinSet = false;
    private boolean isPinBlocked = false;
}
