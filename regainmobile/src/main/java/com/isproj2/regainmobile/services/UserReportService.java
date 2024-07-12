package com.isproj2.regainmobile.services;

import java.util.List;

import com.isproj2.regainmobile.dto.UserReportDTO;

public interface UserReportService {
    UserReportDTO createUserReport(UserReportDTO userReportDTO);
    List<UserReportDTO> getAllUserReports();
}
