package com.isproj2.regainmobile.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isproj2.regainmobile.model.AppUser;
import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByUsername(String username);

}
