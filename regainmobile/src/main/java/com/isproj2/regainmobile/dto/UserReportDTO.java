package com.isproj2.regainmobile.dto;

import java.time.LocalDateTime;

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

    @lombok.NonNull
    private String reportReply;

    private String details;

    @lombok.NonNull
    private LocalDateTime timeStamp;

    @lombok.NonNull
    private String userReportStatus;
}
