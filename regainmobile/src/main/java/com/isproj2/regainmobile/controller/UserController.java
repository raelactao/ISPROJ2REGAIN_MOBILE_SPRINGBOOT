package com.isproj2.regainmobile.controller;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.isproj2.regainmobile.dto.UserDTO;
import com.isproj2.regainmobile.dto.UserIDDTO;
import com.isproj2.regainmobile.exceptions.ImageValidateService;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.exceptions.UserAlreadyExistsException;
import com.isproj2.regainmobile.model.ResponseModel;
import com.isproj2.regainmobile.model.User;
// import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.services.UserService;

import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageValidateService imageValidateService;

    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(
            @RequestParam Integer id,
            @RequestParam(required = false) MultipartFile profileImage,
            @RequestParam(required = false) MultipartFile gcashQRcode,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String role,
            @RequestParam String password,
            @RequestParam String phone,
            @RequestParam(required = false) LocalDate birthday,
            @RequestParam(required = false) String junkshopName) {

        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(id);
            userDTO.setFirstName(firstName);
            userDTO.setLastName(lastName);
            userDTO.setUsername(username);
            userDTO.setEmail(email);
            userDTO.setRole(role);
            userDTO.setPassword(password);
            userDTO.setPhone(phone);
            userDTO.setBirthday(birthday);
            userDTO.setJunkshopName(junkshopName);

            if (profileImage != null && !profileImage.isEmpty()) {
                imageValidateService.validateImageFile(profileImage);
                userDTO.setProfileImage(profileImage.getBytes());
            }
            if (gcashQRcode != null && !gcashQRcode.isEmpty()) {
                imageValidateService.validateImageFile(gcashQRcode);
                userDTO.setGcashQRcode(gcashQRcode.getBytes());
            }

            UserDTO updatedUser = userService.updateUser(userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


    @GetMapping("/seller/by-username/{username}")
    public ResponseModel<Integer> getSellerIdByUsername(@PathVariable String username) {
        try {
            Integer userId = userService.findUserIdByUsername(username); // Fetch user ID by username
            return new ResponseModel<>(HttpStatus.OK.value(), "Seller ID fetched successfully", userId);
        } catch (ResourceNotFoundException e) {
            return new ResponseModel<>(HttpStatus.NOT_FOUND.value(), e.getMessage(), null);
        }
    }

    @PostMapping("/addID")
    public ResponseModel<UserIDDTO> addUserID(@RequestBody UserIDDTO idDTO) {
        try {
            // Delegate to the service layer
            userService.addUserIDDetails(idDTO);
            return new ResponseModel<>(HttpStatus.OK.value(), "User ID saved successfully", idDTO);
        } catch (IllegalArgumentException e) {
            return new ResponseModel<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        } catch (ResourceNotFoundException e) {
            return new ResponseModel<>(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } catch (RuntimeException e) {
            return new ResponseModel<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "An error occurred while saving the user ID: " + e.getMessage());
        }
    }
    


    @GetMapping("/profile-image/{username}")
    public ResponseEntity<String> getProfileImage(@PathVariable String username) {
        byte[] image = userService.getProfileImageByUsername(username);
        if (image != null) {
            String base64Image = Base64.getEncoder().encodeToString(image);
            return ResponseEntity.ok(base64Image);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}/penalty-points")
    public ResponseModel<Integer> getPenaltyPoints(@PathVariable Integer id) {
        try {
            Integer penaltyPoints = userService.getPenaltyPointsByUserId(id);
            if (penaltyPoints == null) {
                penaltyPoints = 0; // Default to 0 if null
            }
            return new ResponseModel<>(HttpStatus.OK.value(), "Penalty points fetched successfully", penaltyPoints);
        } catch (ResourceNotFoundException e) {
            return new ResponseModel<>(HttpStatus.NOT_FOUND.value(), e.getMessage(), null);
        } catch (Exception e) {
            return new ResponseModel<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error", null);
        }
    }


    @GetMapping("/{userId}/reports")
    public ResponseEntity<Map<String, List<?>>> getUserAndListingReports(@PathVariable Integer userId) {
        Map<String, List<?>> combinedReports = userService.getUserAndListingReports(userId);
        return ResponseEntity.ok(combinedReports);
    }

}