package com.isproj2.regainmobile.services;

import java.util.List;

import com.isproj2.regainmobile.dto.FavoriteDTO;
import com.isproj2.regainmobile.model.Favorite;

public interface FavoriteService {
    Favorite addFavorite(FavoriteDTO favoriteDTO);
    void deleteFavorite(Integer favoriteId);
    List<Favorite> getAllFavoritesByUserId(Integer userId);
}
