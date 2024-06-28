package com.isproj2.regainmobile.services.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.dto.UserDTO;
import com.isproj2.regainmobile.model.Role;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.RoleRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private RoleRepository _roleRepository;

    // private final Long TEMP_ROLE = (long) 2;

    @Override
    public User addUser(UserDTO userDTO) {
        // user.setRole(TEMP_ROLE);
        User user = new User(userDTO);
        user.setRole(_roleRepository.findByName(userDTO.getRole()));

        return _userRepository.save(user);
    }

}
