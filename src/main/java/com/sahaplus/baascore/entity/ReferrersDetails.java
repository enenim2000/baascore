package com.sahaplus.baascore.entity;

import jakarta.persistence.Embeddable;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class ReferrersDetails {
    private String referralCountryCode;
    private String referralPhoneNo;
    private String referralName;
}
