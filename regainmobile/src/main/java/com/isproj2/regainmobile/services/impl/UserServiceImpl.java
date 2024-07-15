package com.isproj2.regainmobile.services.impl;

// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.isproj2.regainmobile.dto.UserDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.Role;
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
    public void addUser(UserDTO userDTO) {
        // user.setRole(TEMP_ROLE);
        // UserDTO newUser = new UserDTO();

        String errorMessage = "Username or Contact Number already exists";

        if (_userRepository.existsByUsername(userDTO.getUsername())
                || _userRepository.existsByContactNumber(userDTO.getContactNumber())) {
            throw new ValidationException(errorMessage);
        }

        Role role = _roleRepository.findByName(userDTO.getRole());
        // .orElseThrow(() -> new ResourceNotFoundException("User not found with ID " +
        // userDTO.getRole()));

        User user = new User(userDTO, role);
        _userRepository.save(user);

        return;
    }

    @Override
    public UserDTO login(UserDTO userDTO) {

        String errorMessage = "Invalid credentials";

        User user = _userRepository.findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new ResourceAccessException(errorMessage));

        Role role = _roleRepository.findByName(user.getRole().getName());

        if (user.getPassword().matches(userDTO.getPassword())) {
            UserDTO loginUser = new UserDTO(user);
            loginUser.setRole(role.getName());
            return loginUser;
        } else {
            throw new ValidationException(errorMessage);
        }

    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {

        // String errorMessage = "Username or Contact Number already exists";

        User user = _userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID " + userDTO.getId()));

        Role role = _roleRepository.findByName(user.getRole().getName());
        // .orElseThrow(() -> new ResourceNotFoundException("User not found with ID " +
        // userDTO.getRole()));

        // boolean usernameAlreadyExists =
        // _userRepository.existsByUsername(userDTO.getUsername());
        // boolean contactNumberAlreadyExists =
        // _userRepository.existsByContactNumber(userDTO.getContactNumber());

        // if (usernameAlreadyExists &&
        // !(user.getUsername().equals(userDTO.getUsername()))) {
        // throw new ValidationException(errorMessage);
        // } else if (contactNumberAlreadyExists
        // && !(user.getContactNumber().equals(userDTO.getContactNumber()))) {
        // throw new ValidationException(errorMessage);
        // }

        User updatedUser = new User(userDTO, role);
        return new UserDTO(_userRepository.save(updatedUser));
    }

    // @Override
    // public UserDTO getUserById(Integer userId) {
    // Optional<User> user = _userRepository.findById(userId);
    // return user.orElseThrow(() -> new IllegalArgumentException("User not
    // found"));
    // }

}
