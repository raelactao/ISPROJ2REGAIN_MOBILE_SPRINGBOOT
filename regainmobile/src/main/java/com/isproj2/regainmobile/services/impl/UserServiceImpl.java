package com.isproj2.regainmobile.services.impl;

import java.io.IOException;

// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;

import com.isproj2.regainmobile.dto.UserDTO;
import com.isproj2.regainmobile.dto.UserIDDTO;
import com.isproj2.regainmobile.exceptions.AuthenticationException;
import com.isproj2.regainmobile.exceptions.ImageValidateService;
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

import jakarta.validation.ValidationException;

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

    @Autowired
    private ImageValidateService validationService;

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

        // String errorMessage = "User does not exist";

        User user = _userRepository.findByUsername(userDTO.getUsername().trim())
                .orElseThrow(() -> new AuthenticationException());

        Role role = _roleRepository.findByName(user.getRole().getName());

        // if (user.getPassword().matches(userDTO.getPassword()) && user.getAccountStatus().equals("Active")) {
        //     UserDTO loginUser = new UserDTO(user);
        //     loginUser.setRole(role.getName());
        //     return loginUser;
        // } else if (user.getPassword().matches(userDTO.getPassword())
        //         && user.getAccountStatus().equals("Pending")) {
        //     throw new UserAccountNotActiveException();
        // } else {
        //     throw new AuthenticationException();
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
        // Fetch the existing user
        User user = _userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID " + userDTO.getId()));
    
        // Retrieve the current role
        Role role = _roleRepository.findByName(user.getRole().getName());
    
        // Update user details
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword()); // Ideally, hash the password
        user.setPhone(userDTO.getPhone());
        user.setBirthday(userDTO.getBirthday());
        user.setJunkshopName(userDTO.getJunkshopName());
        user.setRole(role);
    
        // Update images if provided
        if (userDTO.getProfileImage() != null) {
            user.setProfileImage(userDTO.getProfileImage());
        }
        if (userDTO.getGcashQRcode() != null) {
            user.setGcashQr(userDTO.getGcashQRcode());
        }
    
        // Save and return updated user
        return new UserDTO(_userRepository.save(user));
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
    
        User updatedUser = new User(userID.getUser(), foundUser.getRole());
        updatedUser.setUserID(foundUser.getUserID());
        updatedUser.setAccountStatus(foundUser.getAccountStatus());
        _userRepository.save(updatedUser);
    
        // Decode Base64 string to byte[]
        byte[] idImageBytes = userID.getIdImageBytes();
        if (idImageBytes == null) {
            throw new IllegalArgumentException("ID image cannot be null");
        }
    
        UserID newID = new UserID(updatedUser, userID);
        newID.setIdImage(idImageBytes);
        _IdRepository.save(newID);
    
        return;
    }
    

    @Override
    public byte[] getProfileImageByUsername(String username) {
        User user = _userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
        return user.getProfileImage();
    }
    // @Override
    // public void uploadProfileImage(MultipartFile file, Integer userId) throws IOException {
    //     validationService.validateImageFile(file);

    //     User user = _userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    //     user.setProfileImage(file.getBytes());
    //     _userRepository.save(user);
    // }

    // @Override
    // public void uploadGCashQR(MultipartFile file, Integer userId) throws IOException {
    //     validationService.validateImageFile(file);

    //     User user = _userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    //     user.setGcashQr(file.getBytes());
    //     _userRepository.save(user);
    // }

}
