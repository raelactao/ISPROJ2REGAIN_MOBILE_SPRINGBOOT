package com.isproj2.regainmobile.services.impl;

import com.isproj2.regainmobile.dto.FavoriteDTO;
import com.isproj2.regainmobile.model.Favorite;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.model.Product;
import com.isproj2.regainmobile.repo.FavoriteRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.repo.ProductRepository;
import com.isproj2.regainmobile.services.FavoriteService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.favoriteRepository = favoriteRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Favorite addFavorite(FavoriteDTO favoriteDTO) {
        Optional<User> userOptional = userRepository.findById(favoriteDTO.getUserID());
        Optional<Product> productOptional = productRepository.findById(favoriteDTO.getProductID());

        if (userOptional.isPresent() && productOptional.isPresent()) {
            User user = userOptional.get();
            Product product = productOptional.get();
            Favorite favorite = new Favorite(favoriteDTO, user, product);
            return favoriteRepository.save(favorite);
        } else {
            throw new RuntimeException("User or Product not found");
        }
    }

    @Override
    public void deleteFavorite(Integer favoriteID) {
        favoriteRepository.deleteById(favoriteID);
    }

    @Override
    public List<Favorite> getAllFavoritesByUserId(Integer userID) {
        return favoriteRepository.findAllByUser_Id(userID);
    }
}
