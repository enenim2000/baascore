package com.sahaplus.baascore.repository;

import com.sahaplus.baascore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginId(long loginId);

    Optional<User> findById(long userId);


}
