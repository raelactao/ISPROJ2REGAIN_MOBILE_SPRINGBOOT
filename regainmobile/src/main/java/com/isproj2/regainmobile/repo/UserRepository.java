package com.isproj2.regainmobile.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isproj2.regainmobile.model.User;
// import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserID(Integer userID);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByContactNumber(String contactNumber);

}
