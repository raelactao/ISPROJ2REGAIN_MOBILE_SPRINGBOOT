package com.isproj2.regainmobile.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isproj2.regainmobile.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserID(Integer userID);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

}
