package com.isproj2.regainmobile.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.dto.UserDTO;
import com.isproj2.regainmobile.model.Role;
import com.isproj2.regainmobile.repo.RoleRepository;
import com.isproj2.regainmobile.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository _roleRepository;

    @Override
    public Long getRoleId(String name) {
        return _roleRepository.findByName(name).getRoleID();
    }

}
