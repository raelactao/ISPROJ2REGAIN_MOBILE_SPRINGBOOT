package com.isproj2.regainmobile.services.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // temp data while tables don't exist + default values
    private final Long TEMP_ROLE = (long) 2;
    private final Long TEMP_ADDRESS = (long) 1;
    // private final int TEMP_PENALTY_POINTS = 0;
    // private final BigDecimal TEMP_COMMISSION_BAL = new BigDecimal(0.0);
    // private final String TEMP_ACC_STATUS = "Active";

    @Override
    public User addUser(User user) {
        user.setRole(TEMP_ROLE);
        user.setAddress(TEMP_ADDRESS);

        // default values jic
        // user.setPenaltyPoints(TEMP_PENALTY_POINTS);
        // user.setCommissionBalance(TEMP_COMMISSION_BAL);
        // user.setAccountStatus(TEMP_ACC_STATUS);

        return userRepository.save(user);
    }

}
