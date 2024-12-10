package com.isproj2.regainmobile.services;

import java.util.List;

import com.isproj2.regainmobile.dto.CommissionsDTO;
import com.isproj2.regainmobile.dto.CommissionsTotalDTO;

public interface CommissionService {

    List<CommissionsDTO> getCommissionsByUserId(Integer userId);

    CommissionsTotalDTO getCommissionsTotal(Integer userId);

    CommissionsTotalDTO addPaymentForCommissions(List<CommissionsDTO> list, Integer userId);

}
