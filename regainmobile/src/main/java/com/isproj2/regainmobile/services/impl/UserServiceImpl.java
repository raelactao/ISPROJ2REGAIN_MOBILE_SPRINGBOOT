package com.isproj2.regainmobile.services.impl;

// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.isproj2.regainmobile.dto.UserDTO;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.RoleRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.UserService;

import jakarta.validation.ValidationException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private RoleRepository _roleRepository;

    // private final Long TEMP_ROLE = (long) 2;

    @Override
    public void addUser(UserDTO userDTO) throws IllegalArgumentException {
        // user.setRole(TEMP_ROLE);
        // UserDTO newUser = new UserDTO();

        String errorMessage = "Username or Contact Number already exists";

        if (_userRepository.existsByUsername(userDTO.getUsername())
                || _userRepository.existsByContactNumber(userDTO.getContactNumber())) {
            throw new ValidationException(errorMessage);
        }

        User user = new User(userDTO);
        user.setRole(_roleRepository.findByName(userDTO.getRole()));
        _userRepository.save(user);

        return;
    }

    @Override
    public UserDTO login(UserDTO userDTO) {

        String errorMessage = "Invalid credentials";

        User user = _userRepository.findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new ResourceAccessException(errorMessage));

        if (user.getPassword().matches(userDTO.getPassword())) {
            return new UserDTO(user);
        } else {
            throw new ValidationException(errorMessage);
        }

    }

    // @Override
    // public UserDTO getUserById(Integer userId) {
    // Optional<User> user = _userRepository.findById(userId);
    // return user.orElseThrow(() -> new IllegalArgumentException("User not
    // found"));
    // }

}
