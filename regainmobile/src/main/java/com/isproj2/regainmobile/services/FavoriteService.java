package com.isproj2.regainmobile.services;

import java.util.List;

import com.isproj2.regainmobile.dto.FavoriteDTO;
import com.isproj2.regainmobile.dto.ProductDTO;
import com.isproj2.regainmobile.dto.ViewProductDTO;

public interface FavoriteService {
    FavoriteDTO createFavorite(FavoriteDTO favoriteDTO);

    void deleteFavorite(Integer favoriteId);

    void deleteFavoriteByUserProduct(Integer userId, Integer productId);

    FavoriteDTO getFavoriteById(Integer favoriteId);

    List<ViewProductDTO> getFavoritesByUser(Integer userId);
}
