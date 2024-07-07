package com.isproj2.regainmobile.services;

import com.isproj2.regainmobile.dto.UserDTO;
import com.isproj2.regainmobile.model.User;

public interface UserService {

    User addUser(UserDTO user);
    User getUserById(Integer userId);
}