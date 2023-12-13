package com.sahaplus.baascore;

import com.sahaplus.baascore.entity.User;
import com.sahaplus.baascore.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@SpringBootApplication
public class BaascoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaascoreApplication.class, args);
    }

    @Component
    public static class UserRunner implements CommandLineRunner {

        private final UserRepository userRepository;

        public UserRunner(UserRepository userRepository) {
            this.userRepository = userRepository;
        }


        @Override
        public void run(String... args) throws Exception {
            User user = new User();
            user.setLoginId("12");
            user.setEmail("paulakerejola@gmail.com");
            user.setPin(134679);
            user.setBvnVerified(true);
            user.setPhoneVerified(true);
            user.setEmailVerified(true);
            user.setLoginAttempts(1);
            user.setLastLogin(LocalDateTime.now());
            user.setPinAttempts(1);
            user.setLastPinAttempt(LocalDateTime.now());
            user.setPinSet(true);
            user.setPinBlocked(false);
            user.setBlocked(false);
            user.setDeleted(false);
            userRepository.save(user);
        }
    }

}
