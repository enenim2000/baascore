package com.sahaplus.baascore.bankone_apis.util;

import com.sahaplus.baascore.bankone_apis.enums.ResponseCodes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {

  private String responseCode;
  private String responseMessage;

  public BaseResponse() {
    this.responseCode = ResponseCodes.SUCCESSFUL.getValue();
    this.responseMessage = "Successful";
  }
}
