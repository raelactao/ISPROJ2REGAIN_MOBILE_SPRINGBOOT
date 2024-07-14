package com.isproj2.regainmobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.dto.OfferDTO;
import com.isproj2.regainmobile.model.Offer;
import com.isproj2.regainmobile.services.OfferService;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @PostMapping("/add")
    public ResponseEntity<OfferDTO> createOffer(@RequestBody OfferDTO offerDTO) {
        OfferDTO createdOffer = offerService.createOffer(offerDTO);
        return ResponseEntity.ok(createdOffer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfferDTO> updateOffer(@PathVariable("id") Integer offerId, @RequestBody OfferDTO offerDTO) {
        OfferDTO updatedOffer = offerService.updateOffer(offerId, offerDTO);
        return ResponseEntity.ok(updatedOffer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable("id") Integer offerId) {
        offerService.deleteOffer(offerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDTO> getOfferById(@PathVariable("id") Integer offerId) {
        OfferDTO offerDTO = offerService.getOfferById(offerId);
        return ResponseEntity.ok(offerDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OfferDTO>> getAllOffers() {
        List<OfferDTO> offers = offerService.getAllOffers();
        return ResponseEntity.ok(offers);
    }

    @GetMapping("/buyer/{buyerId}")
    public ResponseEntity<List<OfferDTO>> getOffersByBuyer(@PathVariable Integer buyerId) {
        List<OfferDTO> offers = offerService.getOffersByBuyer(buyerId);
        return ResponseEntity.ok(offers);
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<OfferDTO>> getOffersBySeller(@PathVariable Integer sellerId) {
        List<OfferDTO> offers = offerService.getOffersBySeller(sellerId);
        return ResponseEntity.ok(offers);
    }
}