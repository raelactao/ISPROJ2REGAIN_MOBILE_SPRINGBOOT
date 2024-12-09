package com.isproj2.regainmobile.services.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;

import com.isproj2.regainmobile.dto.ListingReportDTO;
import com.isproj2.regainmobile.dto.UserDTO;
import com.isproj2.regainmobile.dto.UserIDDTO;
import com.isproj2.regainmobile.dto.UserProfileUpdateDTO;
import com.isproj2.regainmobile.dto.UserReportDTO;
import com.isproj2.regainmobile.exceptions.AuthenticationException;
import com.isproj2.regainmobile.exceptions.ImageValidateService;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.exceptions.UserAccountNotActiveException;
import com.isproj2.regainmobile.exceptions.UserAlreadyExistsException;
import com.isproj2.regainmobile.model.Role;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.model.UserID;
import com.isproj2.regainmobile.repo.ListingReportRepository;
import com.isproj2.regainmobile.repo.RoleRepository;
import com.isproj2.regainmobile.repo.UserIDRepository;
import com.isproj2.regainmobile.repo.UserReportRepository;
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
        user.setAccountStatus("Pending");
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Hash the password
        _userRepository.save(user);

        return;
    }

    @Override
    public UserDTO login(UserDTO userDTO) {

        User user = _userRepository.findByUsername(userDTO.getUsername().trim())
                .orElseThrow(() -> new AuthenticationException());

        Role role = _roleRepository.findByName(user.getRole().getName());

        if (user.getAccountStatus().equals("Deleted")) {
            throw new AuthenticationException();
        }

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

        // Match hashed password
        if (passwordEncoder.matches(userDTO.getPassword(), user.getPassword())
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

    // @Override
    // public UserDTO updateUser(UserDTO userDTO) {
    // // Fetch the existing user
    // User user = _userRepository.findById(userDTO.getId())
    // .orElseThrow(() -> new ResourceNotFoundException("User not found with ID " +
    // userDTO.getId()));

    // // Retrieve the current role
    // Role role = _roleRepository.findByName(user.getRole().getName());

    // // .orElseThrow(() -> new ResourceNotFoundException("User not found with ID "
    // +
    // // userDTO.getRole()));

    // // User updatedUser = new User(userDTO, role);

    // // String dtoEncoded;
    // // if (userDTO.getPassword() != null || !userDTO.getPassword().isEmpty()) {
    // // dtoEncoded = passwordEncoder.encode(userDTO.getPassword());
    // // } else {
    // // dtoEncoded = user.getPassword();
    // // }
    // // updatedUser.setPassword(dtoEncoded);

    // // return new UserDTO(_userRepository.save(updatedUser));

    // // Update user details
    // user.setFirstName(userDTO.getFirstName());
    // user.setLastName(userDTO.getLastName());
    // user.setUsername(userDTO.getUsername());
    // user.setEmail(userDTO.getEmail());

    // // ensures password stays hashed on update
    // String dtoEncoded;
    // if (userDTO.getPassword() != null || !userDTO.getPassword().isEmpty()) {
    // dtoEncoded = passwordEncoder.encode(userDTO.getPassword());
    // } else {
    // dtoEncoded = user.getPassword();
    // }
    // user.setPassword(dtoEncoded);

    // // user.setPassword(userDTO.getPassword()); // Ideally, hash the password
    // user.setPhone(userDTO.getPhone());
    // user.setBirthday(userDTO.getBirthday().toString());
    // user.setJunkshopName(userDTO.getJunkshopName());
    // user.setRole(role);

    // // Update images if provided
    // if (userDTO.getProfileImage() != null) {
    // user.setProfileImage(userDTO.getProfileImage());
    // }
    // if (userDTO.getGcashQRcode() != null) {
    // user.setGcashQr(userDTO.getGcashQRcode());
    // }

    // // Save and return updated user
    // return new UserDTO(_userRepository.save(user));
    // }

    @Override
    public UserProfileUpdateDTO updateUser(UserProfileUpdateDTO userProfileDTO) {
        // Fetch the existing user
        User existingUser = _userRepository.findById(userProfileDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID " + userProfileDTO.getId()));

        // Update only the provided fields
        if (userProfileDTO.getFirstName() != null) {
            existingUser.setFirstName(userProfileDTO.getFirstName());
        }
        if (userProfileDTO.getLastName() != null) {
            existingUser.setLastName(userProfileDTO.getLastName());
        }
        if (userProfileDTO.getJunkshopName() != null) {
            existingUser.setJunkshopName(userProfileDTO.getJunkshopName());
        }
        if (userProfileDTO.getProfileImage() != null) {
            existingUser.setProfileImage(userProfileDTO.getProfileImage());
        }
        if (userProfileDTO.getGcashQRcode() != null) {
            existingUser.setGcashQr(userProfileDTO.getGcashQRcode());
        }

        // Save the updated user
        User updatedUser = _userRepository.save(existingUser);

        // Convert the updated User entity back to DTO
        UserProfileUpdateDTO responseDTO = new UserProfileUpdateDTO();
        responseDTO.setId(updatedUser.getUserID());
        responseDTO.setFirstName(updatedUser.getFirstName());
        responseDTO.setLastName(updatedUser.getLastName());
        responseDTO.setJunkshopName(updatedUser.getJunkshopName());
        responseDTO.setUsername(updatedUser.getUsername());
        responseDTO.setProfileImage(updatedUser.getProfileImage());
        responseDTO.setGcashQRcode(updatedUser.getGcashQr());

        return responseDTO;
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

        // branch securityRoles
        // String dtoPassword = passwordEncoder.encode(userID.getUser().getPassword());

        User updatedUser;
        if (passwordEncoder.matches(userID.getUser().getPassword(), foundUser.getPassword())) {
            updatedUser = new User(userID.getUser(), foundUser.getRole());
            String dtoEncoded = passwordEncoder.encode(userID.getUser().getPassword());
            updatedUser.setPassword(dtoEncoded);
            updatedUser.setUserID(foundUser.getUserID());
            updatedUser.setAccountStatus(foundUser.getAccountStatus());
            // _userRepository.save(updatedUser);

            // UserID newID = new UserID(updatedUser, userID);
            // _IdRepository.save(newID);
        } else {
            throw new AuthenticationException();
        }

        // User updatedUser = new User(userID.getUser(), foundUser.getRole());
        // updatedUser.setUserID(foundUser.getUserID());
        // updatedUser.setAccountStatus(foundUser.getAccountStatus());
        _userRepository.save(updatedUser);

        // Decode Base64 string to byte[]
        byte[] idImageBytes = userID.getIdImageBytes();
        if (idImageBytes == null) {
            throw new IllegalArgumentException("ID image cannot be null");
        }

        UserID newID = new UserID(updatedUser, userID);
        newID.setIdImage(idImageBytes);
        _IdRepository.save(newID);

        // main
        return;
    }

    @Override
    public byte[] getProfileImageByUsername(String username) {
        User user = _userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
        return user.getProfileImage();
    }
    // @Override
    // public void uploadProfileImage(MultipartFile file, Integer userId) throws
    // IOException {
    // validationService.validateImageFile(file);

    // User user = _userRepository.findById(userId).orElseThrow(() -> new
    // RuntimeException("User not found"));
    // user.setProfileImage(file.getBytes());
    // _userRepository.save(user);
    // }

    // @Override
    // public void uploadGCashQR(MultipartFile file, Integer userId) throws
    // IOException {
    // validationService.validateImageFile(file);

    // User user = _userRepository.findById(userId).orElseThrow(() -> new
    // RuntimeException("User not found"));
    // user.setGcashQr(file.getBytes());
    // _userRepository.save(user);
    // }

    @Override
    public int getPenaltyPointsByUserId(Integer userId) {
        User user = _userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        return user.getPenaltyPoints();
    }

    @Autowired
    private UserReportRepository userReportRepository;

    @Autowired
    private ListingReportRepository listingReportRepository;

    public Map<String, List<?>> getUserAndListingReports(Integer userId) {
        List<UserReportDTO> userReports = userReportRepository.findByReportedUser_UserID(userId)
                .stream()
                .map(UserReportDTO::new)
                .collect(Collectors.toList());

        List<ListingReportDTO> listingReports = listingReportRepository.findByReportedListingSellerUserID(userId)
                .stream()
                .map(ListingReportDTO::new)
                .collect(Collectors.toList());

        Map<String, List<?>> combinedReports = new HashMap<>();
        combinedReports.put("userReports", userReports);
        combinedReports.put("listingReports", listingReports);

        return combinedReports;
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        // Fetch user from repository
        User user = _userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID " + userId));

        // Convert User entity to UserDTO
        return new UserDTO(user);
    }

}
