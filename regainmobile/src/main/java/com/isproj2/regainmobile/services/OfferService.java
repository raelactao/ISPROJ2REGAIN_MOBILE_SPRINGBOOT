package com.isproj2.regainmobile.services;

import java.util.List;

import com.isproj2.regainmobile.dto.OfferDTO;
import com.isproj2.regainmobile.model.Offer;

public interface OfferService {
    OfferDTO createOffer(OfferDTO offerDTO);
    OfferDTO updateOffer(Integer offerId, OfferDTO offerDTO);
    void deleteOffer(Integer offerId);
    OfferDTO getOfferById(Integer offerId);
    List<OfferDTO> getAllOffers();
    List<OfferDTO> getOffersByBuyer(Integer buyerID);
    List<OfferDTO> getOffersBySeller(Integer sellerID);
}
