package com.isproj2.regainmobile.dto;

import java.time.LocalDateTime;

import com.isproj2.regainmobile.model.ListingReport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListingReportDTO {

    private Integer reportID;

    private Integer reporterID;

    private Integer reportedListingID;

    private Integer reasonCategoryID;

    @lombok.NonNull
    private String reportReply;

    private String details;

    @lombok.NonNull
    private LocalDateTime timeStamp;

    @lombok.NonNull
    private String reportStatus;

    public ListingReportDTO(ListingReport listReport) {
        this.reportID = listReport.getReportID();
        this.reporterID = listReport.getReporter().getUserID();
        this.reportedListingID = listReport.getReportedListing().getProductID();
        this.reasonCategoryID = listReport.getReasonCategory().getReportCategoryID();
        this.reportReply = listReport.getReportReply();
        this.details = listReport.getDetails();
        this.timeStamp = listReport.getTimeStamp();
        this.reportStatus = listReport.getReportStatus();
    }

}
