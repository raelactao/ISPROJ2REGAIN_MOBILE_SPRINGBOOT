package com.isproj2.regainmobile.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.dto.FavoriteDTO;
import com.isproj2.regainmobile.model.Favorite;
import com.isproj2.regainmobile.services.FavoriteService;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/add")
    public ResponseEntity<Favorite> addFavorite(@RequestBody FavoriteDTO favoriteDTO) {
        Favorite favorite = favoriteService.addFavorite(favoriteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(favorite);
    }

    @DeleteMapping("/delete/{favoriteId}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Integer favoriteId) {
        favoriteService.deleteFavorite(favoriteId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all/{userID}")
    public ResponseEntity<List<Favorite>> getAllFavoritesByUserId(@PathVariable Integer userID) {
        List<Favorite> favorites = favoriteService.getAllFavoritesByUserId(userID);
        return ResponseEntity.ok(favorites);
    } 
}
