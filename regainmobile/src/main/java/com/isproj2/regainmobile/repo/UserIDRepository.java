package com.isproj2.regainmobile.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isproj2.regainmobile.model.UserID;

@Repository
public interface UserIDRepository extends JpaRepository<UserID, Integer> {

}
