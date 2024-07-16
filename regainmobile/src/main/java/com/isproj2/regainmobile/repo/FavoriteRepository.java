package com.isproj2.regainmobile.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isproj2.regainmobile.model.Favorite;
import com.isproj2.regainmobile.model.Product;
import com.isproj2.regainmobile.model.User;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByProduct(Product product);

    List<Favorite> findByUser(User user);

    List<Favorite> findByProductProductID(Integer productId);

    List<Favorite> findByUserUserID(Integer userId);
}
