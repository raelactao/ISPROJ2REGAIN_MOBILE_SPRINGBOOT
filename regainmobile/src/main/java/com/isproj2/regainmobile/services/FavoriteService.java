package com.isproj2.regainmobile.services;

import java.util.List;

import com.isproj2.regainmobile.dto.FavoriteDTO;

public interface FavoriteService {
    FavoriteDTO createFavorite(FavoriteDTO favoriteDTO);
    void deleteFavorite(Integer favoriteId);
    FavoriteDTO getFavoriteById(Integer favoriteId);
}
