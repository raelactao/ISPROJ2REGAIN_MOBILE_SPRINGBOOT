package com.isproj2.regainmobile.model;

import java.time.LocalDateTime;

import com.isproj2.regainmobile.dto.EQListingReportDTO;

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
@Table(name = "eqlistingreports")
@Entity
public class EQListingReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Integer reportID;

    @ManyToOne
    @JoinColumn(name = "reporter", referencedColumnName = "user_id", nullable = false)
    private User reporter;

    @ManyToOne
    @JoinColumn(name = "reported_eqlisting", referencedColumnName = "equipment_id", nullable = false)
    private Equipment reportedEQListing;

    @ManyToOne
    @JoinColumn(name = "reason_category", referencedColumnName = "report_category_id", nullable = false)
    private ReportCategory reasonCategory;

    @lombok.NonNull
    @Column(name = "report_reply")
    private String reportReply;

    @Column(name = "details")
    private String details;

    @lombok.NonNull
    @Column(name = "time_stamp")
    private LocalDateTime timeStamp;

    @lombok.NonNull
    @Column(name = "report_status")
    private String reportStatus;
    
    public EQListingReport(EQListingReportDTO eqListingReportDTO, User reporter, Equipment reportedEQListing, ReportCategory reasonCategory) {
        this.reportID = eqListingReportDTO.getReportID();
        this.reporter = reporter;
        this.reportedEQListing = reportedEQListing;
        this.reasonCategory = reasonCategory;
        this.reportReply = eqListingReportDTO.getReportReply();
        this.details = eqListingReportDTO.getDetails();
        this.timeStamp = eqListingReportDTO.getTimeStamp();
        this.reportStatus = eqListingReportDTO.getReportStatus();
    }
}
