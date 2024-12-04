package com.isproj2.regainmobile.services.impl;

// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.isproj2.regainmobile.dto.UserDTO;
import com.isproj2.regainmobile.dto.UserIDDTO;
import com.isproj2.regainmobile.exceptions.AuthenticationException;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.exceptions.UserAccountNotActiveException;
import com.isproj2.regainmobile.exceptions.UserAlreadyExistsException;
import com.isproj2.regainmobile.model.Role;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.model.UserID;
import com.isproj2.regainmobile.repo.RoleRepository;
import com.isproj2.regainmobile.repo.UserIDRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private RoleRepository _roleRepository;

    @Autowired
    private UserIDRepository _IdRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addUser(UserDTO userDTO) {

        if (_userRepository.existsByUsername(userDTO.getUsername().trim())
                || _userRepository.existsByEmail(userDTO.getEmail().trim())) {
            throw new UserAlreadyExistsException();
        }

        Role role = _roleRepository.findByName(userDTO.getRole());
        // .orElseThrow(() -> new ResourceNotFoundException("User not found with ID " +
        // userDTO.getRole()));

        User user = new User(userDTO, role);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Hash the password
        _userRepository.save(user);

        return;
    }

    @Override
    public UserDTO login(UserDTO userDTO) {

        User user = _userRepository.findByUsername(userDTO.getUsername().trim())
                .orElseThrow(() -> new AuthenticationException());

        Role role = _roleRepository.findByName(user.getRole().getName());

        // if (user.getPassword().matches(userDTO.getPassword()) &&
        // user.getAccountStatus().equals("Active")) {
        // UserDTO loginUser = new UserDTO(user);
        // loginUser.setRole(role.getName());
        // return loginUser;
        // } else if (user.getPassword().matches(userDTO.getPassword())
        // && user.getAccountStatus().equals("Pending")) {
        // throw new UserAccountNotActiveException();
        // } else {
        // throw new AuthenticationException();
        // }

        if (passwordEncoder.matches(userDTO.getPassword(), user.getPassword()) // Match hashed password
                && "Active".equals(user.getAccountStatus())) {
            UserDTO loginUser = new UserDTO(user);
            loginUser.setRole(user.getRole().getName());
            return loginUser;
        } else if (passwordEncoder.matches(userDTO.getPassword(), user.getPassword())
                && "Pending".equals(user.getAccountStatus())) {
            throw new UserAccountNotActiveException();
        } else {
            throw new AuthenticationException();
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

        User updatedUser = new User(userDTO, role);
        return new UserDTO(_userRepository.save(updatedUser));
    }

    @Override
    public String getUsernameById(Integer userId) {
        return _userRepository.findById(userId)
                .map(User::getUsername) // Assuming User has a `getUsername()` method
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User findById(Integer userId) {
        return _userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
    }

    @Override
    public Integer findUserIdByUsername(String username) {
        User user = _userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
        return user.getUserID(); // Assuming User has a getId() method
    }

    @Override
    public void addUserIDDetails(UserIDDTO userID) {

        User foundUser = _userRepository.findByUsername(userID.getUser().getUsername())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with username " + userID.getUser().getUsername()));

        // String dtoPassword = passwordEncoder.encode(userID.getUser().getPassword());

        if (passwordEncoder.matches(userID.getUser().getPassword(), foundUser.getPassword())) {
            User updatedUser = new User(userID.getUser(), foundUser.getRole());
            String dtoEncoded = passwordEncoder.encode(userID.getUser().getPassword());
            updatedUser.setPassword(dtoEncoded);
            updatedUser.setUserID(foundUser.getUserID());
            updatedUser.setAccountStatus(foundUser.getAccountStatus());
            _userRepository.save(updatedUser);

            UserID newID = new UserID(updatedUser, userID);
            _IdRepository.save(newID);
        } else {
            throw new AuthenticationException();
        }

        return;
    }

}
