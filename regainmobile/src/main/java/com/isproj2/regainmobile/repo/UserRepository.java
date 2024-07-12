package com.isproj2.regainmobile.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isproj2.regainmobile.model.User;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserID(Integer userID);

}
