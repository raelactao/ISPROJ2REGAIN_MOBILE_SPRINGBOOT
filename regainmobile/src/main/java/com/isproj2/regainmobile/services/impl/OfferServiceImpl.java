package com.isproj2.regainmobile.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.dto.OfferDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.Offer;
import com.isproj2.regainmobile.model.Product;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.OfferRepository;
import com.isproj2.regainmobile.repo.ProductRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.OfferService;

@Service
public class OfferServiceImpl implements OfferService {

        @Autowired
        private OfferRepository offerRepository;

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private UserRepository userRepository;

        @Override
        public OfferDTO createOffer(OfferDTO offerDTO) {
                User buyer = userRepository.findById(offerDTO.getBuyerID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Buyer not found with id " + offerDTO.getBuyerID()));

                User seller = userRepository.findById(offerDTO.getSellerID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Seller not found with id " + offerDTO.getSellerID()));

                Product product = productRepository.findById(offerDTO.getProductID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Product not found with id " + offerDTO.getProductID()));

                Offer offer = new Offer(offerDTO, buyer, product, seller);
                offerRepository.save(offer);
                return offerDTO;
        }

        @Override
        public OfferDTO updateOffer(Integer offerId, OfferDTO offerDTO) {
                Offer offer = offerRepository.findById(offerId)
                                .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id " + offerId));

                User buyer = userRepository.findById(offerDTO.getBuyerID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Buyer not found with id " + offerDTO.getBuyerID()));

                User seller = userRepository.findById(offerDTO.getSellerID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Seller not found with id " + offerDTO.getSellerID()));

                Product product = productRepository.findById(offerDTO.getProductID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Product not found with id " + offerDTO.getProductID()));

                offer.setProduct(product);
                offer.setBuyer(buyer);
                offer.setOfferValue(offerDTO.getOfferValue());
                offer.setIsAccepted(offerDTO.getIsAccepted());
                offer.setSeller(seller);

                offerRepository.save(offer);
                return offerDTO;
        }

        @Override
        public void deleteOffer(Integer offerId) {
                if (!offerRepository.existsById(offerId)) {
                        throw new ResourceNotFoundException("Offer not found with id " + offerId);
                }
                offerRepository.deleteById(offerId);
        }

        @Override
        public OfferDTO getOfferById(Integer offerId) {
                Offer offer = offerRepository.findById(offerId)
                                .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id " + offerId));
                return new OfferDTO(offer.getOfferID(), offer.getProduct().getProductID(), offer.getBuyer().getUserID(),
                                offer.getOfferValue(), offer.getIsAccepted(), offer.getSeller().getUserID());
        }

        @Override
        public List<OfferDTO> getAllOffers() {
                return offerRepository.findAll().stream()
                                .map(offer -> new OfferDTO(offer.getOfferID(), offer.getProduct().getProductID(),
                                                offer.getBuyer().getUserID(), offer.getOfferValue(),
                                                offer.getIsAccepted(), offer.getSeller().getUserID()))
                                .collect(Collectors.toList());
        }
}
