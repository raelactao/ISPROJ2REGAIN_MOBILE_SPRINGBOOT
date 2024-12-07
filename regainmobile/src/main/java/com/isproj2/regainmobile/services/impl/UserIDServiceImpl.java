package com.isproj2.regainmobile.services.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.isproj2.regainmobile.exceptions.ImageValidateService;
import com.isproj2.regainmobile.model.UserID;
import com.isproj2.regainmobile.repo.UserIDRepository;
import com.isproj2.regainmobile.services.UserIDService;

@Service
public class UserIDServiceImpl implements UserIDService{

    @Autowired
    private UserIDRepository userIDRepository;

    @Autowired
    private ImageValidateService validationService;

    // @Override
    // public void uploadIDImage(MultipartFile file, Integer userId) throws IOException {
    //     validationService.validateImageFile(file);

    //     UserID userID = userIDRepository.findByUser_UserID(userId).orElse(new UserID());
    //     userID.setIdImage(file.getBytes());
    //     userIDRepository.save(userID);
    // }
    
}
