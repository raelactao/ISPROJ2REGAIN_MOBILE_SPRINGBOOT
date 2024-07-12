package com.isproj2.regainmobile.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isproj2.regainmobile.dto.EQListingReportDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.EQListingReport;
import com.isproj2.regainmobile.model.Equipment;
import com.isproj2.regainmobile.model.ReportCategory;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.EQListingReportRepository;
import com.isproj2.regainmobile.repo.EquipmentRepository;
import com.isproj2.regainmobile.repo.ReportCategoryRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.EQListingReportService;

@Service
public class EQListingReportServiceImpl implements EQListingReportService {

        @Autowired
        private EQListingReportRepository eqlistingReportRepository;

        @Autowired
        private EquipmentRepository equipmentRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private ReportCategoryRepository reportCategoryRepository;

        @Override
        @Transactional
        public EQListingReportDTO createEQListingReport(EQListingReportDTO eqListingReportDTO) {
                User reporter = userRepository.findById(eqListingReportDTO.getReporterID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Reporter not found with id " + eqListingReportDTO.getReporterID()));

                Equipment reportedEQListing = equipmentRepository.findById(eqListingReportDTO.getReportedEQListingID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Reported Equipment Listing not found with id "
                                                                + eqListingReportDTO.getReportedEQListingID()));

                ReportCategory reasonCategory = reportCategoryRepository
                                .findById(eqListingReportDTO.getReasonCategoryID())
                                .orElseThrow(() -> new ResourceNotFoundException("Reason Category not found with id "
                                                + eqListingReportDTO.getReasonCategoryID()));

                EQListingReport eqListingReport = new EQListingReport(eqListingReportDTO, reporter, reportedEQListing,
                                reasonCategory);
                eqListingReport.setTimeStamp(LocalDateTime.now());
                eqlistingReportRepository.save(eqListingReport);
                return eqListingReportDTO;
        }

        @Override
        public List<EQListingReportDTO> getAllEQListingReports() {
                return eqlistingReportRepository.findAll().stream()
                                .map(eqListingReport -> new EQListingReportDTO(eqListingReport.getReportID(),
                                                eqListingReport.getReporter().getUserID(),
                                                eqListingReport.getReportedEQListing().getEquipmentID(),
                                                eqListingReport.getReasonCategory().getReportCategoryID(),
                                                eqListingReport.getReportReply(), eqListingReport.getDetails(),
                                                eqListingReport.getTimeStamp(), eqListingReport.getReportStatus()))
                                .collect(Collectors.toList());
        }

}
