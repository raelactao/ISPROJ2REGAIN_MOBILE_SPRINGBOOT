package com.isproj2.regainmobile.services.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.dto.UserDTO;
import com.isproj2.regainmobile.model.Role;
import com.isproj2.regainmobile.model.AppUser;
import com.isproj2.regainmobile.repo.RoleRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.security.AESEncryptor;
import com.isproj2.regainmobile.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private RoleRepository _roleRepository;

    @Autowired
    private AESEncryptor _aesEncryptor;

    // private final Long TEMP_ROLE = (long) 2;

    @Override
    public AppUser addUser(UserDTO userDTO) {
        // user.setRole(TEMP_ROLE);
        AppUser user = new AppUser(userDTO);
        user.setRole(_roleRepository.findByName(userDTO.getRole()));
        user.setPassword(_aesEncryptor.encrypt(userDTO.getPassword()));

        return _userRepository.save(user);
    }

}
