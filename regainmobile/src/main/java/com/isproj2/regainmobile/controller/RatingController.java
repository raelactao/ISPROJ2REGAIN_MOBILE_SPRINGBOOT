package com.isproj2.regainmobile.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.isproj2.regainmobile.dto.RatingDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.Ratings;
import com.isproj2.regainmobile.services.RatingService;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/add")
    public ResponseEntity<?> addRating(@RequestBody RatingDTO ratingDTO) {
        try {
            RatingDTO newRating = ratingService.addRating(ratingDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newRating);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the rating.");
        }
    }

    @PutMapping("/update-comment/{ratingId}")
    public ResponseEntity<?> updateComment(
            @PathVariable Integer ratingId,
            @RequestBody Map<String, String> payload) {
        try {
            String newComment = payload.get("comments");
            RatingDTO updatedRating = ratingService.updateComment(ratingId, newComment);
            return ResponseEntity.ok(updatedRating);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the comment.");
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserRatings(@PathVariable Integer userId) {
        try {
            List<RatingDTO> ratings = ratingService.getRatingsByUserId(userId);
            return ResponseEntity.ok(ratings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching the ratings.");
        }
    }

}
