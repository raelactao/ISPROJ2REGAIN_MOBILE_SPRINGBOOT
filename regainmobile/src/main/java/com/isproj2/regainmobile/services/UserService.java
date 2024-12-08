package com.isproj2.regainmobile.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.isproj2.regainmobile.dto.UserDTO;
import com.isproj2.regainmobile.dto.UserIDDTO;
import com.isproj2.regainmobile.model.User;

public interface UserService {

    void addUser(UserDTO user);

    UserDTO updateUser(UserDTO user);

    UserDTO login(UserDTO user);

    String getUsernameById(Integer userId);

    User findById(Integer userId);

    Integer findUserIdByUsername(String username);

    void addUserIDDetails(UserIDDTO userID);

    byte[] getProfileImageByUsername(String username);

    // void uploadProfileImage(MultipartFile file, Integer userId) throws IOException;

    // void uploadGCashQR(MultipartFile file, Integer userId) throws IOException;

    // UserDTO getUserById(Integer userId);
}