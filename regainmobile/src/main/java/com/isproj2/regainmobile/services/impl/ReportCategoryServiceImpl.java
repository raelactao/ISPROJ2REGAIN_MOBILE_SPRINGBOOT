package com.isproj2.regainmobile.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.repo.ReportCategoryRepository;
import com.isproj2.regainmobile.services.ReportCategoryService;

@Service
public class ReportCategoryServiceImpl implements ReportCategoryService {

    @Autowired
    private ReportCategoryRepository reportCategoryRepository;

    @Override
    public Integer getReportCategoryId(String name) {
        return reportCategoryRepository.findByName(name).getReportCategoryID();
    }

}
