package com.isproj2.regainmobile.dto;

import java.time.LocalDateTime;

import com.isproj2.regainmobile.model.ListingReport;
import com.isproj2.regainmobile.model.UserReport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReportDTO {

    private Integer userReportID;

    private Integer reporterID;

    private Integer reportedUserID;

    private Integer reasonCategoryID;

    private String reportReply;

    private String details;

    @lombok.NonNull
    private LocalDateTime timeStamp;

    @lombok.NonNull
    private String userReportStatus = "Pending";

    public UserReportDTO(UserReport userReport) {
        this.userReportID = userReport.getUserReportID();
        this.reporterID = userReport.getReporter().getUserID();
        this.reportedUserID = userReport.getReportedUser().getUserID();
        this.reasonCategoryID = userReport.getReasonCategory().getReportCategoryID();
        this.reportReply = userReport.getReportReply();
        this.details = userReport.getDetails();
        this.timeStamp = userReport.getTimeStamp();
        this.userReportStatus = userReport.getUserReportStatus();
    }
}
