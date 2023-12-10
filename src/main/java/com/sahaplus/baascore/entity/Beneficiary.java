package com.sahaplus.baascore.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Beneficiary extends BaseClass {

    private String nameOnAccount;
    private String accountNumber;
    private String bankName;
    private String bankCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User user;
}
