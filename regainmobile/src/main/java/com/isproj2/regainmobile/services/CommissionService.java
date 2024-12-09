package com.isproj2.regainmobile.services;

import java.util.List;

import com.isproj2.regainmobile.dto.CommissionsDTO;

public interface CommissionService {

    List<CommissionsDTO> getCommissionsByUserId(Integer userId);

}
