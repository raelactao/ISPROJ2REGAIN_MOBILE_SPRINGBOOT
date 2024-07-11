package com.isproj2.regainmobile.services;

import java.util.List;

import com.isproj2.regainmobile.dto.EQListingReportDTO;

public interface EQListingReportService {
    EQListingReportDTO createEQListingReport(EQListingReportDTO eqListingReportDTO);
    List<EQListingReportDTO> getAllEQListingReports();
}
