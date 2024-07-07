package com.isproj2.regainmobile.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isproj2.regainmobile.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
