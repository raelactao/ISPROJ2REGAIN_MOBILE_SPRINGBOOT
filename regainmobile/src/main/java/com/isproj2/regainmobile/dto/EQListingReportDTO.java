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
public class EQListingReportDTO {

    private Integer reportID;

    private Integer reporterID;

    private Integer reportedEQListingID;

    private Integer reasonCategoryID;

    @lombok.NonNull
    private String reportReply;

    private String details;

    @lombok.NonNull
    private LocalDateTime timeStamp;

    @lombok.NonNull
    private String reportStatus;
}
