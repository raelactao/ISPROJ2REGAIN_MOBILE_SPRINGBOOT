package com.isproj2.regainmobile.services;

import java.util.List;

import com.isproj2.regainmobile.dto.ListingReportDTO;

public interface ListingReportService {
    ListingReportDTO createListingReport(ListingReportDTO listingReportDTO);

    List<ListingReportDTO> getAllListingReports();

    List<ListingReportDTO> getListingReportsByReportedUser(Integer reportedId);

}
