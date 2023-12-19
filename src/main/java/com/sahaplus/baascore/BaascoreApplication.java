package com.sahaplus.baascore;

import com.sahaplus.baascore.auth.RequestUtil;
import com.sahaplus.baascore.bankone_apis.enums.CustomerType;
import com.sahaplus.baascore.bankone_apis.enums.Gender;
import com.sahaplus.baascore.entity.User;
import com.sahaplus.baascore.repository.UserRepository;
import com.sahaplus.baascore.service.impl.PermissionService;
import com.sahaplus.baascore.util.SyncPermissionRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.time.LocalDateTime;

@SpringBootApplication
public class BaascoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaascoreApplication.class, args);
    }

    @Component
    public static class UserRunner implements CommandLineRunner {

        private final UserRepository userRepository;

        private final PermissionService permissionService;

        private final RequestMappingHandlerMapping requestMappingHandlerMapping;

        public UserRunner(UserRepository userRepository, PermissionService permissionService, RequestMappingHandlerMapping requestMappingHandlerMapping) {
            this.userRepository = userRepository;
            this.permissionService = permissionService;
            this.requestMappingHandlerMapping = requestMappingHandlerMapping;
        }


        @Override
        public void run(String... args) throws Exception {
            User user = new User();
            user.setFirstName("Ngolo");
            user.setLastName("Kante");
            user.setGender(Gender.MALE);
            user.setDateOfBirth("1995-01-01T00:00");
            user.setOtherNames("Paul");
            user.setNin("76534324565");
            user.setLoginId("12");
            user.setEmail("paulakerejola@gmail.com");
            user.setPin(134679);
            user.setBvnVerified(false);
            user.setLoginAttempts(1);
            user.setLastLogin(LocalDateTime.now());
            user.setPinAttempts(1);
            user.setLastPinAttempt(LocalDateTime.now());
            user.setPinSet(true);
            user.setPinBlocked(false);
            userRepository.save(user);
        }
//
//        @Override
//        public void run(String... args) throws Exception {
//
//            SyncPermissionRequest syncPermissionRequest = permissionService.generatePermissionRequest(requestMappingHandlerMapping);
//
//            RestTemplate restTemplate = new RestTemplate();
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("client-id", "HpI9e0J9bZrMG/uYaTV2UH6TrvzgyhU0aOJ4tE7MHbCZIOEYVc0MgiFfimU/M7wtpdMmvIRks8ctv1nA8ve9ZRDgej1tiEJ0L3xyyiToNoxE0CpTpKHg7XzjgOqfD1JK40laHCYVSlsMEMML4YuXDYJI5Kn0ZyjZs/Ax8imOJIcegBeUbVPAFrR7sEKI43ezhmc9knjUqgbgmKa73IxCnPIGjNBsHM8cyCR4SOMOaXX4t0dKXnI4f3yHguinDYHY1Zr1AZ31SL+TnvE5HIEnKPiHGCGwzlLpfis05L0Wq3spFtLjjd50KzQjkOUt3lAkKCr+pS6vcjrl+fU/OfiQyg==");
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<SyncPermissionRequest> entity = new HttpEntity<>(syncPermissionRequest, headers);
//            String url = "http://localhost:9690/permission/sync";
//
//            restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
////
////            permissionService.syncApplicationPermission(syncPermissionRequest);
//            //System.out.println("SyncPermissionRequest: " + new Gson().toJson(syncPermissionRequest));
//		/*Map<String, String> keys = RSAUtil.generateKeyPair();
//		System.out.println("public-key: " + keys.get("public-key"));
//		System.out.println("private-key: " + keys.get("private-key"));*/
//
//        }
//    }

        }
    }
//}
