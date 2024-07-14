package com.isproj2.regainmobile.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isproj2.regainmobile.dto.ListingReportDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.ListingReport;
import com.isproj2.regainmobile.model.Product;
import com.isproj2.regainmobile.model.ReportCategory;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.ListingReportRepository;
import com.isproj2.regainmobile.repo.ProductRepository;
import com.isproj2.regainmobile.repo.ReportCategoryRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.ListingReportService;

@Service
public class ListingReportServiceImpl implements ListingReportService {

        @Autowired
        private ListingReportRepository listingReportRepository;

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private ReportCategoryRepository reportCategoryRepository;

        @Override
        @Transactional
        public ListingReportDTO createListingReport(ListingReportDTO listingReportDTO) {
                User reporter = userRepository.findById(listingReportDTO.getReporterID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Reporter not found with id " + listingReportDTO.getReporterID()));

                Product reportedListing = productRepository.findById(listingReportDTO.getReportedListingID())
                                .orElseThrow(() -> new ResourceNotFoundException("Reported Listing not found with id "
                                                + listingReportDTO.getReportedListingID()));

                ReportCategory reasonCategory = reportCategoryRepository
                                .findById(listingReportDTO.getReasonCategoryID())
                                .orElseThrow(() -> new ResourceNotFoundException("Reason Category not found with id "
                                                + listingReportDTO.getReasonCategoryID()));

                ListingReport listingReport = new ListingReport(listingReportDTO, reporter, reportedListing,
                                reasonCategory);
                listingReport.setTimeStamp(LocalDateTime.now());
                listingReportRepository.save(listingReport);
                return listingReportDTO;
        }

        @Override
        public List<ListingReportDTO> getAllListingReports() {
                return listingReportRepository.findAll().stream()
                                .map(listingReport -> new ListingReportDTO(listingReport.getReportID(),
                                                listingReport.getReporter().getUserID(),
                                                listingReport.getReportedListing().getProductID(),
                                                listingReport.getReasonCategory().getReportCategoryID(),
                                                listingReport.getReportReply(), listingReport.getDetails(),
                                                listingReport.getTimeStamp(), listingReport.getReportStatus()))
                                .collect(Collectors.toList());
        }

        @Override
        public List<ListingReportDTO> getListingReportsByReportedUser(Integer reportedId) {

                List<ListingReportDTO> reportsByReportedSeller = new ArrayList<ListingReportDTO>();

                List<ListingReport> listReportsBySeller = listingReportRepository
                                .findByReportedListingSellerUserID(reportedId);

                for (ListingReport report : listReportsBySeller) {
                        reportsByReportedSeller.add(new ListingReportDTO(report));
                }

                // User reportedSeller = userRepository.findById(reportedId)
                // .orElseThrow(() -> new ResourceNotFoundException(
                // "Seller of reported list not found with id "
                // + reportedId));

                // List<Product> userProducts = productRepository.findBySeller(reportedSeller);
                // List<ListingReport> allListingReports = listingReportRepository.findAll();

                // // not the most efficient
                // for (ListingReport report : allListingReports) {
                // for (Product product : userProducts) {
                // if (product.equals(report.getReportedListing())) {
                // ListingReportDTO newReport = new ListingReportDTO(report);
                // reportsByReportedSeller.add(newReport);
                // }
                // }
                // }

                return reportsByReportedSeller;
        }

}
