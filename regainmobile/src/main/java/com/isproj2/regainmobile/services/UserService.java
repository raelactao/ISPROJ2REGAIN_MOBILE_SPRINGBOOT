package com.isproj2.regainmobile.services;
import com.isproj2.regainmobile.dto.UserDTO;
import com.isproj2.regainmobile.model.User;

public interface UserService {

    void addUser(UserDTO user);

    UserDTO updateUser(UserDTO user);

    UserDTO login(UserDTO user);

    String getUsernameById(Integer userId);

    User findById(Integer userId);
    
    Integer findUserIdByUsername(String username);

    // UserDTO getUserById(Integer userId);
}