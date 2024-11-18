package com.isproj2.regainmobile.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.dto.GreenZoneDTO;
import com.isproj2.regainmobile.model.GreenZone;
import com.isproj2.regainmobile.services.GreenZoneService;

@RestController
@RequestMapping("/api/green_zone")
public class GreenZoneController {

    @Autowired
    private GreenZoneService greenZoneService;

    // New endpoint for JSON data for the mobile app
    @GetMapping("/articles")
    public ResponseEntity<List<GreenZoneDTO>> getRecentArticles() {
        List<GreenZone> articles = greenZoneService.getRecentArticles();
        List<GreenZoneDTO> articleDTOs = articles.stream()
                .map(article -> new GreenZoneDTO(
                        article.getId(),
                        article.getTitle(),
                        article.getLink(),
                        article.getSummary(),
                        article.getDate()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(articleDTOs);
    }

}
