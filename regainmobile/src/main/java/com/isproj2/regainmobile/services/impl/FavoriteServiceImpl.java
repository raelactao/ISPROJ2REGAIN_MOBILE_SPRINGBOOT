package com.isproj2.regainmobile.services.impl;

import com.isproj2.regainmobile.dto.FavoriteDTO;
import com.isproj2.regainmobile.model.Favorite;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.model.Product;
import com.isproj2.regainmobile.repo.FavoriteRepository;
import com.isproj2.regainmobile.services.FavoriteService;
import com.isproj2.regainmobile.services.ProductService;
import com.isproj2.regainmobile.services.UserService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

   private final FavoriteRepository favoriteRepository;
    private final UserService userService;
    private final ProductService productService;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, UserService userService, ProductService productService) {
        this.favoriteRepository = favoriteRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public Favorite addFavorite(FavoriteDTO favoriteDTO) {
        User user = userService.getUserById(favoriteDTO.getUserID());
        Product product = productService.getProductById(favoriteDTO.getProductID());

        Favorite favorite = new Favorite(favoriteDTO, user, product);
        return favoriteRepository.save(favorite);
    }

    @Override
    public void deleteFavorite(Integer favoriteId) {
        favoriteRepository.deleteById(favoriteId);
    }

    @Override
    public List<Favorite> getAllFavoritesByUserId(Integer userId) {
        return favoriteRepository.findAllByUser_Id(userId);
    }
}
