package com.isproj2.regainmobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.dto.ListingReportDTO;
import com.isproj2.regainmobile.dto.UserReportDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.services.UserReportService;

@RestController
@RequestMapping("/api/userreports")
public class UserReportController {

    @Autowired
    private UserReportService userReportService;

    @PostMapping("/add")
    public ResponseEntity<?> createUserReport(@RequestBody UserReportDTO userReportDTO) {
        try {
            UserReportDTO createdUserReport = userReportService.createUserReport(userReportDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUserReport);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the report.");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserReportDTO>> getAllUserReports() {
        List<UserReportDTO> userReports = userReportService.getAllUserReports();
        return ResponseEntity.ok(userReports);
    }
}
