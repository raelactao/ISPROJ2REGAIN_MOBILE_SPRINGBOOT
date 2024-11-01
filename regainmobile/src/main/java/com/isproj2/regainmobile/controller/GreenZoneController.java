package com.isproj2.regainmobile.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.model.GreenZone;
import com.isproj2.regainmobile.services.GreenZoneService;

@RestController
public class GreenZoneController {

    @Autowired
    private GreenZoneService greenZoneService;

    @GetMapping("/api/scrape-news")
    public List<GreenZone> getGreenZoneNews(@RequestParam String url) throws IOException {
        return greenZoneService.getGreenZoneNews(url);
    }
}
