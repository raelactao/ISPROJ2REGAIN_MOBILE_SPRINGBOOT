package com.isproj2.regainmobile.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.isproj2.regainmobile.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}