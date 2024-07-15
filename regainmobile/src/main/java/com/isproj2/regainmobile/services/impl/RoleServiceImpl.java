package com.isproj2.regainmobile.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.model.Role;
import com.isproj2.regainmobile.repo.RoleRepository;
import com.isproj2.regainmobile.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository _roleRepository;

    @Override
    public Integer getRoleId(String name) {
        Role role = _roleRepository.findByName(name);
        return role.getRoleID();
    }

}
