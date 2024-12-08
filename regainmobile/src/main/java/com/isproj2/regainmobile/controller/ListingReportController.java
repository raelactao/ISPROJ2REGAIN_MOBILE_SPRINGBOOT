package com.isproj2.regainmobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.dto.ListingReportDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.services.ListingReportService;

@RestController
@RequestMapping("/api/listingreports")
public class ListingReportController {

    @Autowired
    private ListingReportService listingReportService;

    @PostMapping("/add")
    public ResponseEntity<?> createListingReport(@RequestBody ListingReportDTO listingReportDTO) {
        try {
            ListingReportDTO createdListingReport = listingReportService.createListingReport(listingReportDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdListingReport);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the report.");
        }
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
