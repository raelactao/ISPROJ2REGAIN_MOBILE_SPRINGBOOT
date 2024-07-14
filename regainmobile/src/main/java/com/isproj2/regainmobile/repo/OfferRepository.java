package com.isproj2.regainmobile.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isproj2.regainmobile.model.Offer;
import com.isproj2.regainmobile.model.User;

import java.util.List;


public interface OfferRepository extends JpaRepository<Offer, Integer> {
    List<Offer> findByBuyer(User buyer);
    List<Offer> findBySeller(User seller);
}
