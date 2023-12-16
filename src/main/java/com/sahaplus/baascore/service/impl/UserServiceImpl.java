package com.sahaplus.baascore.service.impl;

import com.sahaplus.baascore.dto.request.UpdateUserDto;
import com.sahaplus.baascore.entity.User;
import com.sahaplus.baascore.exception.ApiException;
import com.sahaplus.baascore.repository.UserRepository;
import com.sahaplus.baascore.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserDetails(String loginId) {
        return userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new ApiException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(UpdateUserDto updateUserDto, String loginId) {
        User dbUser = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new ApiException("User Not Found"));

        dbUser.setKYCVerified(updateUserDto.isKYCVerified());
        dbUser.setDeleted(updateUserDto.isDeleted());
        dbUser.setBlocked(updateUserDto.isBlocked());
        dbUser.setNin(updateUserDto.getNin());
        dbUser.setPin(updateUserDto.getPin());
        dbUser.setFirstName(updateUserDto.getFirstName());
        dbUser.setLastName(updateUserDto.getLastName());
        dbUser.setMobile_phone(updateUserDto.getMobile_phone());
        dbUser.setDateOfBirth(updateUserDto.getDateOfBirth());
        dbUser.setAddress(updateUserDto.getAddress());
        dbUser.setCustomerType(updateUserDto.getCustomerType());
        dbUser.setEmailVerified(updateUserDto.isEmailVerified());
        dbUser.setPhoneVerified(updateUserDto.isPhoneVerified());
        dbUser.setProfileComplete(updateUserDto.isProfileComplete());
        dbUser.setBvnVerified(updateUserDto.isBvnVerified());
        dbUser.setBlocked(updateUserDto.isBlocked());
        dbUser.setDeleted(updateUserDto.isDeleted());
        dbUser.setPinSet(updateUserDto.isPinSet());
        dbUser.setPinBlocked(updateUserDto.isPinBlocked());

        userRepository.save(dbUser);
    }


}
