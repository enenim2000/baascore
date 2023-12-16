package com.sahaplus.baascore.auth;

import lombok.Data;

import java.util.List;

@Data
public class AuthToken {

  private String companyCode;

  private String companyName;

  private String uuid;

  private String email;

  private String phone;

  private String username;

  private String lang;

  private String accessToken;

  private String refreshToken;

  private boolean isEmailVerified;

  private boolean isPhoneVerified;

  List<String> audience;

  private String expires;
}
