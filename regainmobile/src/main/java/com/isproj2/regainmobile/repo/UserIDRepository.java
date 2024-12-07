package com.isproj2.regainmobile.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isproj2.regainmobile.model.UserID;

@Repository
public interface UserIDRepository extends JpaRepository<UserID, Integer> {

    Optional<UserID> findByUser_UserID(Integer userId);

}
