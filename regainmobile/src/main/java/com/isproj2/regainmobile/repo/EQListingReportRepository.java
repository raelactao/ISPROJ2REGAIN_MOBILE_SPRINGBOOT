package com.isproj2.regainmobile.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isproj2.regainmobile.model.EQListingReport;

@Repository
public interface EQListingReportRepository extends JpaRepository<EQListingReport, Integer> {
    
}
