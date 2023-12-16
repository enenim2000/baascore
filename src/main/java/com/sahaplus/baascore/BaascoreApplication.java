package com.sahaplus.baascore;

import com.sahaplus.baascore.repository.UserRepository;
import com.sahaplus.baascore.service.impl.PermissionService;
import com.sahaplus.baascore.util.SyncPermissionRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
public class BaascoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaascoreApplication.class, args);
    }

//    @Component
//    public static class UserRunner implements CommandLineRunner {
//
//        private final UserRepository userRepository;
//
//        private final PermissionService permissionService;
//
//        private final RequestMappingHandlerMapping requestMappingHandlerMapping;
//
//        public UserRunner(UserRepository userRepository, PermissionService permissionService, RequestMappingHandlerMapping requestMappingHandlerMapping) {
//            this.userRepository = userRepository;
//            this.permissionService = permissionService;
//            this.requestMappingHandlerMapping = requestMappingHandlerMapping;
//        }


//        @Override
//        public void run(String... args) throws Exception {
//            User user = new User();
//            user.setLoginId("12");
//            user.setEmail("paulakerejola@gmail.com");
//            user.setPin(134679);
//            user.setBvnVerified(true);
//            user.setPhoneVerified(true);
//            user.setEmailVerified(true);
//            user.setLoginAttempts(1);
//            user.setLastLogin(LocalDateTime.now());
//            user.setPinAttempts(1);
//            user.setLastPinAttempt(LocalDateTime.now());
//            user.setPinSet(true);
//            user.setPinBlocked(false);
//            user.setBlocked(false);
//            user.setDeleted(false);
//            userRepository.save(user);
//        }

//        @Override
//        public void run(String... args) throws Exception {
//
//            SyncPermissionRequest syncPermissionRequest = permissionService.generatePermissionRequest(requestMappingHandlerMapping);
//
//            RestTemplate restTemplate = new RestTemplate();
//
//            String url = "http://localhost:9690/permission/sync";
//
//            restTemplate.exchange(url, HttpMethod.POST,null, String.class);
//
////            permissionService.syncApplicationPermission(syncPermissionRequest);
//            //System.out.println("SyncPermissionRequest: " + new Gson().toJson(syncPermissionRequest));
//		/*Map<String, String> keys = RSAUtil.generateKeyPair();
//		System.out.println("public-key: " + keys.get("public-key"));
//		System.out.println("private-key: " + keys.get("private-key"));*/
//
//        }
//    }

}
