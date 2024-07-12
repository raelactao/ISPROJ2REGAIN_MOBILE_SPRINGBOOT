package com.isproj2.regainmobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.dto.UserReportDTO;
import com.isproj2.regainmobile.services.UserReportService;

@RestController
@RequestMapping("/api/userreports")
public class UserReportController {
    
    @Autowired
    private UserReportService userReportService;
    
    @PostMapping("/add")
    public ResponseEntity<UserReportDTO> createUserReport(@RequestBody UserReportDTO userReportDTO) {
        UserReportDTO createdUserReport = userReportService.createUserReport(userReportDTO);
        return ResponseEntity.ok(createdUserReport);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserReportDTO>> getAllUserReports() {
        List<UserReportDTO> userReports = userReportService.getAllUserReports();
        return ResponseEntity.ok(userReports);
    }
}
