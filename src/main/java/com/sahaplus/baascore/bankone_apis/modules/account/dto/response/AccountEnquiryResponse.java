package com.sahaplus.baascore.bankone_apis.modules.account.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountEnquiryResponse {
    private String Name;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Nuban;
    private String Number;
    private String ProductCode;
    private String BVN;
    private double AvailableBalance;
    private double LedgerBalance;
    private String Status;
    private String Tier;
    private double MaximumBalance;
    private String MaximumDeposit;
    private boolean IsSuccessful;
    private String ResponseMessage;
    private String PNDStatus;
    private String LienStatus;
    private String FreezeStatus;
    private String RequestStatus;
}
