package com.isproj2.regainmobile.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isproj2.regainmobile.dto.FavoriteDTO;
import com.isproj2.regainmobile.dto.ProductDTO;
import com.isproj2.regainmobile.dto.ViewProductDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.Favorite;
import com.isproj2.regainmobile.model.Product;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.FavoriteRepository;
import com.isproj2.regainmobile.repo.ProductRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.FavoriteService;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// import java.util.stream.Collectors;

// import javax.swing.text.View;

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
        Favorite savedFave = favoriteRepository.save(favorite);
        return new FavoriteDTO(savedFave);
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

    @Override
    public List<ViewProductDTO> getFavoritesByUser(Integer userId) {
        List<ViewProductDTO> dtoList = new ArrayList<ViewProductDTO>();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        List<Favorite> favoritesOfUser = favoriteRepository.findByUser(user);
        Collections.reverse(favoritesOfUser);
        for (Favorite fave : favoritesOfUser) {
            dtoList.add(new ViewProductDTO(fave.getProduct(), fave.getIsFavorite()));
        }
        return dtoList;
    }

    @Override
    public void deleteFavoriteByUserProduct(Integer userId, Integer productId) {

        List<Favorite> userFaves = favoriteRepository.findByUserUserID(userId);
        List<Favorite> faveProducts = favoriteRepository.findByProductProductID(productId);
        userFaves.retainAll(faveProducts);

        for (Favorite fave : userFaves) {
            favoriteRepository.deleteById(fave.getFavoriteID());
        }

        return;
    }

}