package com.isproj2.regainmobile.services;

import com.isproj2.regainmobile.dto.UserDTO;
import com.isproj2.regainmobile.model.AppUser;

public interface UserService {

    AppUser addUser(UserDTO user);

}