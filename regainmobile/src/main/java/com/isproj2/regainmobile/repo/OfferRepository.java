package com.isproj2.regainmobile.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isproj2.regainmobile.dto.ViewOfferDTO;
import com.isproj2.regainmobile.model.Offer;
import com.isproj2.regainmobile.model.Product;
import com.isproj2.regainmobile.model.User;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    List<Offer> findByBuyer(User buyer);
    List<Offer> findBySeller(User seller);
    List<Offer> findByProduct(Product product);
}
