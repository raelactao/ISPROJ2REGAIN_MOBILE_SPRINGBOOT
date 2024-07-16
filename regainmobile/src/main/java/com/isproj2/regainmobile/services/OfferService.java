package com.isproj2.regainmobile.services;

import java.util.List;

import com.isproj2.regainmobile.dto.OfferDTO;
import com.isproj2.regainmobile.dto.ViewOfferDTO;

public interface OfferService {
    // OfferDTO createOffer(OfferDTO offerDTO);
    ViewOfferDTO updateOffer(Integer offerId, ViewOfferDTO offerDTO);
    void deleteOffer(Integer offerId);
    OfferDTO getOfferById(Integer offerId);
    List<OfferDTO> getAllOffers();
    List<OfferDTO> getOffersByBuyer(Integer buyerId);
    List<OfferDTO> getOffersBySeller(Integer sellerId);
    List<ViewOfferDTO> getAllViewOffers();
    List<ViewOfferDTO> getViewOffersByBuyer(Integer buyerId);
    List<ViewOfferDTO> getViewOffersByProductID(Integer productId);
    ViewOfferDTO addOffer(ViewOfferDTO offerDTO);
}
