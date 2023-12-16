package com.sahaplus.baascore.controller;

import com.sahaplus.baascore.dto.request.UpdateUserDto;
import com.sahaplus.baascore.entity.User;
import com.sahaplus.baascore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<User> getUserById(@RequestParam ("loginId") String loginId) {
        return ResponseEntity.ok(userService.getUserDetails(loginId));
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/")
    public void updateUserDetails(UpdateUserDto userDto, String loginId) {
        userService.updateUser(userDto, loginId);
    }
}
