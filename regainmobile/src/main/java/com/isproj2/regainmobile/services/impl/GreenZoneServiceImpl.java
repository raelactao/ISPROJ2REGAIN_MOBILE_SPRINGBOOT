package com.isproj2.regainmobile.services.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.model.GreenZone;
import com.isproj2.regainmobile.repo.GreenZoneRepository;
import com.isproj2.regainmobile.services.GreenZoneService;
import com.isproj2.regainmobile.services.util.GreenZoneDateSort;

@Service
public class GreenZoneServiceImpl implements GreenZoneService {
    @Autowired
    private GreenZoneRepository greenZoneRepository;

    @Override
    public List<GreenZone> getRecentArticles() {

        LocalDate dateNow = LocalDate.now();

        Collection<GreenZone> recentList = greenZoneRepository.findByDateBetween(dateNow.minus(5, ChronoUnit.WEEKS),
                dateNow);

        List<GreenZone> sortedList = recentList.stream()
                .sorted(new GreenZoneDateSort())
                .collect(Collectors.toList());

        return sortedList;
    }

}
