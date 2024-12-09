package com.isproj2.regainmobile.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.dto.CommissionsDTO;
import com.isproj2.regainmobile.dto.OrderDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.Commissions;
import com.isproj2.regainmobile.repo.CommissionsRepository;
import com.isproj2.regainmobile.repo.FavoriteRepository;
import com.isproj2.regainmobile.services.CommissionService;

@Service
public class CommissionServiceImpl implements CommissionService {

    @Autowired
    CommissionsRepository commissionsRepository;

    @Autowired
    FavoriteRepository favoriteRepository;

    @Override
    public List<CommissionsDTO> getCommissionsByUserId(Integer userId) {

        List<Commissions> userCommissions = commissionsRepository.findByUserUserID(userId);

        // for (Commissions comm : userCommissions) {

        // }

        return userCommissions.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    private CommissionsDTO convertToDTO(Commissions comm) {
        CommissionsDTO dto = new CommissionsDTO(comm);
        return dto;
    }

}
