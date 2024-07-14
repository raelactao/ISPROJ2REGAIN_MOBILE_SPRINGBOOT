package com.isproj2.regainmobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.dto.ListingReportDTO;
import com.isproj2.regainmobile.services.ListingReportService;

@RestController
@RequestMapping("/api/listingreports")
public class ListingReportController {

    @Autowired
    private ListingReportService listingReportService;

    @PostMapping("/add")
    public ResponseEntity<ListingReportDTO> createListingReport(@RequestBody ListingReportDTO listingReportDTO) {
        ListingReportDTO createdListingReport = listingReportService.createListingReport(listingReportDTO);
        return ResponseEntity.ok(createdListingReport);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ListingReportDTO>> getAllListingReports() {
        List<ListingReportDTO> listingReports = listingReportService.getAllListingReports();
        return ResponseEntity.ok(listingReports);
    }

    @GetMapping("/list/{userID}")
    public ResponseEntity<List<ListingReportDTO>> getReportsByReportedSeller(@PathVariable("userID") Integer userID) {
        List<ListingReportDTO> listingReportsOnSeller = listingReportService.getListingReportsByReportedUser(userID);
        return ResponseEntity.ok(listingReportsOnSeller);
    }

}
