package com.isproj2.regainmobile.services.impl;

import com.isproj2.regainmobile.dto.FavoriteDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.Favorite;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.model.Product;
import com.isproj2.regainmobile.repo.FavoriteRepository;
import com.isproj2.regainmobile.repo.ProductRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.FavoriteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public FavoriteDTO createFavorite(FavoriteDTO favoriteDTO) {
        User user = userRepository.findById(favoriteDTO.getUserID())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + favoriteDTO.getUserID()));

        Product product = productRepository.findById(favoriteDTO.getProductID())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Product not found with id " + favoriteDTO.getProductID()));

        Favorite favorite = new Favorite(favoriteDTO, user, product);
        favoriteRepository.save(favorite);
        return favoriteDTO;
    }

    @Override
    public void deleteFavorite(Integer favoriteId) {
        favoriteRepository.deleteById(favoriteId);
    }

    @Override
    public FavoriteDTO getFavoriteById(Integer favoriteId) {
        Favorite favorite = favoriteRepository.findById(favoriteId)
                .orElseThrow(() -> new ResourceNotFoundException("Favorite not found with id " + favoriteId));
        return new FavoriteDTO(favorite.getFavoriteID(), favorite.getUser().getUserID(),
                favorite.getProduct().getProductID(), favorite.getIsFavorite());
    }
}