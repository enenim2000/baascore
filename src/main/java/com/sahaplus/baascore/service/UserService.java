package com.sahaplus.baascore.service;

import com.sahaplus.baascore.dto.request.UpdateUserDto;
import com.sahaplus.baascore.entity.User;

import java.util.List;

public interface UserService {
    User getUserDetails(String loginId);

    List<User> getAllUsers();

    void updateUser(UpdateUserDto updateUserDto, String loginId);
}
