package com.isproj2.regainmobile.model;

import java.time.LocalDateTime;

import com.isproj2.regainmobile.dto.ListingReportDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "listingreports")
@Entity
public class ListingReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Integer reportID;

    @ManyToOne
    @JoinColumn(name = "reporter", referencedColumnName = "user_id")
    private User reporter;

    @ManyToOne
    @JoinColumn(name = "reported_listing", referencedColumnName = "product_id")
    private Product reportedListing;

    @ManyToOne
    @JoinColumn(name = "reason_category", referencedColumnName = "report_category_id")
    private ReportCategory reasonCategory;

    // @lombok.NonNull
    @Column(name = "report_reply", length = 255)
    private String reportReply;

    @Column(name = "details", length = 255)
    private String details;

    @lombok.NonNull
    @Column(name = "time_stamp")
    private LocalDateTime timeStamp;

    @lombok.NonNull
    @Column(name = "report_status", columnDefinition = "Pending", length = 20)
    private String reportStatus;

    public ListingReport(ListingReportDTO listingReportDTO, User reporter, Product reportedListing,
            ReportCategory reasonCategory) {
        this.reportID = listingReportDTO.getReportID();
        this.reporter = reporter;
        this.reportedListing = reportedListing;
        this.reasonCategory = reasonCategory;
        this.reportReply = listingReportDTO.getReportReply();
        this.details = listingReportDTO.getDetails();
        this.timeStamp = listingReportDTO.getTimeStamp();
        this.reportStatus = listingReportDTO.getReportStatus();
    }

}
