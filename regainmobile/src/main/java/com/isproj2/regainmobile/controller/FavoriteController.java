package com.isproj2.regainmobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.dto.FavoriteDTO;
import com.isproj2.regainmobile.services.FavoriteService;


@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<FavoriteDTO> createFavorite(@RequestBody FavoriteDTO favoriteDTO) {
        FavoriteDTO createdFavorite = favoriteService.createFavorite(favoriteDTO);
        return ResponseEntity.ok(createdFavorite);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable("id") Integer favoriteId) {
        favoriteService.deleteFavorite(favoriteId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteDTO> getFavoriteById(@PathVariable("id") Integer favoriteId) {
        FavoriteDTO favoriteDTO = favoriteService.getFavoriteById(favoriteId);
        return ResponseEntity.ok(favoriteDTO);
    }
    
}