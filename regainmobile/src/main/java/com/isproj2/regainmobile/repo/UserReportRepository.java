package com.isproj2.regainmobile.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isproj2.regainmobile.model.UserReport;

@Repository
public interface UserReportRepository extends JpaRepository<UserReport, Integer> {
    List<UserReport> findByReportedUser_UserID(Integer reportedUserID);
}
