package com.isproj2.regainmobile.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isproj2.regainmobile.model.GreenZone;

@Repository
public interface GreenZoneRepository extends JpaRepository<GreenZone, Integer> {

    boolean existsByLink(String link);

    List<GreenZone> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
