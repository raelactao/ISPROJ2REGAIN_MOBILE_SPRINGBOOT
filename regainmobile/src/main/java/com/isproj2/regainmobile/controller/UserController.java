package com.isproj2.regainmobile.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.dto.UserDTO;
import com.isproj2.regainmobile.dto.UserIDDTO;
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

    @PutMapping("/update")
    public ResponseModel<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser;
        try {
            savedUser = userService.updateUser(userDTO);
        } catch (UserAlreadyExistsException e) {
            return new ResponseModel<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }

        return new ResponseModel<>(HttpStatus.OK.value(), "User saved", savedUser);
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
        // UserIDDTO addedID;
        try {
            userService.addUserIDDetails(idDTO);
        } catch (RuntimeException e) {
            return new ResponseModel<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }

        return new ResponseModel<>(HttpStatus.OK.value(), "User saved", idDTO);

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