package com.isproj2.regainmobile.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isproj2.regainmobile.model.ListingReport;
import com.isproj2.regainmobile.model.Product;

import java.util.List;

@Repository
public interface ListingReportRepository extends JpaRepository<ListingReport, Integer> {
    List<ListingReport> findByReportedListing(Product reportedListing);

    List<ListingReport> findByReportedListingSellerUserID(Integer sellerID);
}
