package com.isproj2.regainmobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.dto.EQListingReportDTO;
import com.isproj2.regainmobile.services.EQListingReportService;

@RestController
@RequestMapping("/api/eqlistings")
public class EQListingReportController {

    @Autowired
    private EQListingReportService eqListingReportService;
    
    @PostMapping("/add")
    public ResponseEntity<EQListingReportDTO> createEQListingReport(@RequestBody EQListingReportDTO eqListingReportDTO) {
        EQListingReportDTO createdEQListingReport = eqListingReportService.createEQListingReport(eqListingReportDTO);
        return ResponseEntity.ok(createdEQListingReport);
    }

    @GetMapping("/list")
    public ResponseEntity<List<EQListingReportDTO>> getAllEQListingReports() {
        List<EQListingReportDTO>  eqListingReports = eqListingReportService.getAllEQListingReports();
        return ResponseEntity.ok(eqListingReports);
    }
}
