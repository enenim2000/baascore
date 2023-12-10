package com.sahaplus.baascore.entity;

import com.sahaplus.baascore.enums.Gender;
import com.sahaplus.baascore.enums.Title;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class NextOfKinDetails {
    private Title nextOfKinTitle;
    private String nextOfKinName;
    private String nextOfKinEmail;
    private Gender nextOfKinGender;
    private String nextOfKinDateOfBirth;
    private String nextOfKinOccupation;
    private String nextOfKinRelationship;
    private String nextOfKinAddress;
    private String nextOfKinPhoneNo;
    private String nextOfKinCountryCode;
}
