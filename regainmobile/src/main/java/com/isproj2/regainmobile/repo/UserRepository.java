package com.isproj2.regainmobile.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isproj2.regainmobile.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
