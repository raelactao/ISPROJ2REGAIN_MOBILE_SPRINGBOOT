package com.isproj2.regainmobile.services;

import java.util.List;

import com.isproj2.regainmobile.dto.OfferDTO;

public interface OfferService {
    OfferDTO createOffer(OfferDTO offerDTO);
    OfferDTO updateOffer(Integer offerId, OfferDTO offerDTO);
    void deleteOffer(Integer offerId);
    OfferDTO getOfferById(Integer offerId);
    List<OfferDTO> getAllOffers();
}
