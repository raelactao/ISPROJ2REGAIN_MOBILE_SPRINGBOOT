package com.isproj2.regainmobile.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.isproj2.regainmobile.model.ReportCategory;

public interface ReportCategoryRepository extends JpaRepository<ReportCategory, Integer> {

        ReportCategory findByName(String name);
    
}
