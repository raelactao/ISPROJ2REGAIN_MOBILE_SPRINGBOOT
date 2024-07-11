package com.isproj2.regainmobile.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isproj2.regainmobile.model.ListingReport;

@Repository
public interface ListingReportRepository extends JpaRepository<ListingReport, Integer> {
    
}
