package com.sahaplus.baascore.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BVNDetails {
    private String maritalStatus;
    private String gender;
    private String surname;
    private String middleName;
    private String firstName;
    private String enrollUserName;
    private String nationality;
    private String stateOfOrigin;
    private String lgaOfOrigin;
    private String nin;
    private String remarks;
    private String dateOfBirth;
    private String watchlisted;
    private String additionalInfo1;
    private String faceImage;
}

