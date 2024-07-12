package com.isproj2.regainmobile.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.dto.UserReportDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.ReportCategory;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.model.UserReport;
import com.isproj2.regainmobile.repo.ReportCategoryRepository;
import com.isproj2.regainmobile.repo.UserReportRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.UserReportService;

@Service
public class UserReportServiceImpl implements UserReportService {

    @Autowired
    private UserReportRepository userReportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReportCategoryRepository reportCategoryRepository;

    @Override
    public UserReportDTO createUserReport(UserReportDTO userReportDTO) {
        User reporter = userRepository.findById(userReportDTO.getReporterID())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                                "Reporter not found with id " + userReportDTO.getReporterID()));

        User reportedUser = userRepository.findById(userReportDTO.getReportedUserID())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                                "Reported User not found with id " + userReportDTO.getReportedUserID()));

        ReportCategory reasonCategory = reportCategoryRepository
                        .findById(userReportDTO.getReasonCategoryID())
                        .orElseThrow(() -> new ResourceNotFoundException("Reason Category not found with id "
                                        + userReportDTO.getReasonCategoryID()));
        
        UserReport userReport = new UserReport(userReportDTO, reporter, reportedUser, reasonCategory);
        userReport.setTimeStamp(LocalDateTime.now());
        return userReportDTO;
    }

    @Override
    public List<UserReportDTO> getAllUserReports() {
                return userReportRepository.findAll().stream()
                                .map(userReport -> new UserReportDTO(userReport.getUserReportID(),
                                                userReport.getReporter().getUserID(),
                                                userReport.getReportedUser().getUserID(),
                                                userReport.getReasonCategory().getReportCategoryID(),
                                                userReport.getReportReply(), userReport.getDetails(),
                                                userReport.getTimeStamp(), userReport.getUserReportStatus()))
                                .collect(Collectors.toList());
    }
    
}
