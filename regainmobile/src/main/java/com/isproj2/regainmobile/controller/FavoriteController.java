package com.isproj2.regainmobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.isproj2.regainmobile.dto.ViewProductDTO;
import com.isproj2.regainmobile.model.ResponseModel;
import com.isproj2.regainmobile.services.FavoriteService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/add")
    public ResponseEntity<FavoriteDTO> createFavorite(@RequestBody FavoriteDTO favoriteDTO) {
        FavoriteDTO createdFavorite = favoriteService.createFavorite(favoriteDTO);
        return ResponseEntity.ok(createdFavorite);
    }

    // @PostMapping("/add/{prodId}/{}")
    // public ResponseEntity<FavoriteDTO> addFavorite(@PathVariable FavoriteDTO
    // favoriteDTO) {
    // FavoriteDTO createdFavorite = favoriteService.addFavorite(favoriteDTO);
    // return ResponseEntity.ok(createdFavorite);
    // }

    @DeleteMapping("/delete/{id}")
    public ResponseModel<Void> deleteFavorite(@PathVariable("id") Integer favoriteId) {
        favoriteService.deleteFavorite(favoriteId);
        return new ResponseModel<>(HttpStatus.OK.value(), "Favorite deleted");
    }

    @DeleteMapping("/delete/{userId}/{productId}")
    public ResponseModel<Void> deleteFavoriteByUserProduct(@PathVariable("userId") Integer userId,
            @PathVariable("productId") Integer productId) {
        favoriteService.deleteFavoriteByUserProduct(userId, productId);
        return new ResponseModel<>(HttpStatus.OK.value(), "Favorite deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteDTO> getFavoriteById(@PathVariable("id") Integer favoriteId) {
        FavoriteDTO favoriteDTO = favoriteService.getFavoriteById(favoriteId);
        return ResponseEntity.ok(favoriteDTO);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<List<ViewProductDTO>> getFavoritesByUser(@PathVariable("id") Integer userId) {
        List<ViewProductDTO> faveList = favoriteService.getFavoritesByUser(userId);
        return ResponseEntity.ok(faveList);
    }

}