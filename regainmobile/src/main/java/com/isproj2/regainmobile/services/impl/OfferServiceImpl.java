package com.isproj2.regainmobile.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.dto.OfferDTO;
import com.isproj2.regainmobile.dto.ViewOfferDTO;
import com.isproj2.regainmobile.dto.ViewProductDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.Favorite;
import com.isproj2.regainmobile.model.Offer;
import com.isproj2.regainmobile.model.Order;
import com.isproj2.regainmobile.model.Product;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.FavoriteRepository;
import com.isproj2.regainmobile.repo.OfferRepository;
import com.isproj2.regainmobile.repo.OrderRepository;
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

        @Autowired
        private FavoriteRepository favoriteRepository;

        @Autowired
        private OrderRepository orderRepository;

        // @Override
        // public OfferDTO createOffer(OfferDTO offerDTO) {
        // User buyer = userRepository.findById(offerDTO.getBuyerID())
        // .orElseThrow(() -> new ResourceNotFoundException(
        // "Buyer not found with id " + offerDTO.getBuyerID()));

        // User seller = userRepository.findById(offerDTO.getSellerID())
        // .orElseThrow(() -> new ResourceNotFoundException(
        // "Seller not found with id " + offerDTO.getSellerID()));

        // Product product = productRepository.findById(offerDTO.getProductID())
        // .orElseThrow(() -> new ResourceNotFoundException(
        // "Product not found with id " + offerDTO.getProductID()));

        // Offer offer = new Offer(offerDTO, buyer, product, seller);
        // offerRepository.save(offer);
        // return offerDTO;
        // }

        @Override
        public ViewOfferDTO addOffer(ViewOfferDTO offerDTO) {
                User buyer = userRepository.findByUsername(offerDTO.getBuyerName())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Buyer not found with username " + offerDTO.getBuyerName()));

                User seller = userRepository.findByUsername(offerDTO.getSellerName())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Seller not found with username " + offerDTO.getSellerName()));

                Product product = productRepository.findById(offerDTO.getProduct().getProductID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Product not found with id " + offerDTO.getProduct().getProductID()));

                Offer offer = new Offer(
                                offerDTO.getOfferID(),
                                product,
                                buyer,
                                new BigDecimal(offerDTO.getOfferValue()),
                                offerDTO.getIsAccepted(),
                                seller);

                offerRepository.save(offer);
                return offerDTO;
        }

        @Override
        public ViewOfferDTO updateOffer(Integer offerId, ViewOfferDTO offerDTO) {
                Offer offer = offerRepository.findById(offerId)
                                .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id " + offerId));

                User buyer = userRepository.findByUsername(offerDTO.getBuyerName())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Buyer not found with username " + offerDTO.getBuyerName()));

                User seller = userRepository.findByUsername(offerDTO.getSellerName())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Seller not found with username " + offerDTO.getSellerName()));

                Product product = productRepository.findById(offerDTO.getProduct().getProductID())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Product not found with id " + offerDTO.getProduct().getProductID()));

                List<Offer> offersByProduct = offerRepository.findByProduct(product);

                if (offerDTO.getIsAccepted() == true) {
                        for (Offer otherOffer : offersByProduct) {
                                otherOffer.setIsAccepted(false);
                                offerRepository.save(otherOffer);
                        }
                }

                offer.setProduct(product);
                offer.setBuyer(buyer);
                offer.setOfferValue(new BigDecimal(offerDTO.getOfferValue()));
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
                                offer.getOfferValue().toString(), offer.getIsAccepted(), offer.getSeller().getUserID());
        }

        @Override
        public List<OfferDTO> getAllOffers() {
                return offerRepository.findAll().stream()
                                .map(offer -> new OfferDTO(offer.getOfferID(), offer.getProduct().getProductID(),
                                                offer.getBuyer().getUserID(), offer.getOfferValue().toString(),
                                                offer.getIsAccepted(), offer.getSeller().getUserID()))
                                .collect(Collectors.toList());
        }

        @Override
        public List<OfferDTO> getOffersByBuyer(Integer buyerId) {
                User buyer = userRepository.findById(buyerId)
                                .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id " + buyerId));
                List<Offer> offers = offerRepository.findByBuyer(buyer);
                return offers.stream().map(this::convertToDTO).collect(Collectors.toList());
        }

        @Override
        public List<OfferDTO> getOffersBySeller(Integer sellerId) {
                User seller = userRepository.findById(sellerId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Offer not found with id " + sellerId));
                List<Offer> offers = offerRepository.findBySeller(seller);
                return offers.stream().map(this::convertToDTO).collect(Collectors.toList());
        }

        private OfferDTO convertToDTO(Offer offer) {

                return new OfferDTO(
                                offer.getOfferID(),
                                offer.getProduct().getProductID(),
                                offer.getBuyer().getUserID(),
                                offer.getOfferValue().toString(),
                                offer.getIsAccepted(),
                                offer.getSeller().getUserID());
        }

        @Override
        public List<ViewOfferDTO> getAllViewOffers() {
                List<Offer> offers = offerRepository.findAll();
                return offers.stream()
                                .map(this::convertToViewOfferDTO)
                                .collect(Collectors.toList());
        }

        @Override
        public List<ViewOfferDTO> getViewOffersByBuyer(Integer buyerId) {

                User buyer = userRepository.findById(buyerId)
                                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + buyerId));

                List<Offer> offers = offerRepository.findByBuyer(buyer);
                return offers.stream()
                                .map(this::convertToViewOfferDTO)
                                .collect(Collectors.toList());
        }

        @Override
        public List<ViewOfferDTO> getViewOffersByProductID(Integer productId) {
                Product product = productRepository.findById(productId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Product not found with id " + productId));

                List<Offer> offers = offerRepository.findByProduct(product);
                return offers.stream()
                                .map(this::convertToViewOfferDTO)
                                .collect(Collectors.toList());
        }

        private ViewOfferDTO convertToViewOfferDTO(Offer offer) {
                Boolean isOrdered = false;
                // if (offer.getIsAccepted()) {
                // isOrdered = true;
                // }

                List<Favorite> favoritesList = favoriteRepository.findByUser(offer.getBuyer());
                boolean faved = false;
                for (Favorite fave : favoritesList) {
                        if (offer.getProduct().equals(fave.getProduct())) {
                                faved = true;
                        }
                }

                List<Order> orderList = orderRepository.findByBuyer(offer.getBuyer());

                for (Order order : orderList) {
                        if (offer.getProduct().equals(order.getProduct())) {
                                isOrdered = true;
                        }

                }

                ViewOfferDTO dto = new ViewOfferDTO();
                dto.setOfferID(offer.getOfferID());
                dto.setProduct(new ViewProductDTO(offer.getProduct(), faved));
                dto.setBuyerName(offer.getBuyer().getUsername()); // Example: Assuming User has a username
                dto.setOfferValue(offer.getOfferValue().toString());
                dto.setIsAccepted(offer.getIsAccepted());
                dto.setIsOrdered(isOrdered);
                dto.setSellerName(offer.getSeller().getUsername()); // Example: Assuming User has a username
                return dto;
        }
}
