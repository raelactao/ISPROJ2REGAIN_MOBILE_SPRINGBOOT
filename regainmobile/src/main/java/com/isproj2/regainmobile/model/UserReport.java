package com.isproj2.regainmobile.model;

import java.time.LocalDateTime;

import com.isproj2.regainmobile.dto.UserReportDTO;

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
@Table(name = "userreports")
@Entity
public class UserReport {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_report_id")
    private Integer userReportID;

    @ManyToOne
    @JoinColumn(name = "reporter", referencedColumnName = "user_id", nullable = false)
    private User reporter;

    @ManyToOne
    @JoinColumn(name = "reported_user", referencedColumnName = "user_id", nullable = false)
    private User reportedUser;

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
    @Column(name = "user_report_status")
    private String userReportStatus;

    public UserReport(UserReportDTO userReportDTO, User reporter, User reportedUser, ReportCategory reasonCategory) {
        this.userReportID = userReportDTO.getUserReportID();
        this.reporter = reporter;
        this.reportedUser = reportedUser;
        this.reasonCategory = reasonCategory;
        this.reportReply = userReportDTO.getReportReply();
        this.details = userReportDTO.getDetails();
        this.timeStamp = userReportDTO.getTimeStamp();
        this.userReportStatus = userReportDTO.getUserReportStatus();
    }

}
