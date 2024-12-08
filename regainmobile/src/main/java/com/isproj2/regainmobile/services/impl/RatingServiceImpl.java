package com.isproj2.regainmobile.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.dto.RatingDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.Ratings;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.RatingRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public RatingDTO addRating(RatingDTO ratingDTO) {
        User ratedUser = userRepository.findById(ratingDTO.getRatedUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Rated user not found"));
        User ratedByUser = userRepository.findById(ratingDTO.getRatedByUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Rating user not found"));

        Ratings rating = new Ratings();
        rating.setRatedUserID(ratedUser);
        rating.setRatedByUserID(ratedByUser); // Current App User
        rating.setRateValue(ratingDTO.getRateValue());
        rating.setComments(ratingDTO.getComments());
        rating.setDateCreated(LocalDateTime.now()); // Set current date and time

        Ratings savedRating = ratingRepository.save(rating);

        return new RatingDTO(savedRating);
    }
    
    @Override
    public RatingDTO updateComment(Integer ratingId, String newComment) {
        Ratings rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found with ID: " + ratingId));

        rating.setComments(newComment);
        rating.setDateEdited(LocalDateTime.now()); 

        Ratings updatedRating = ratingRepository.save(rating);
        return new RatingDTO(updatedRating);
    }

    @Override
    public List<RatingDTO> getRatingsByUserId(Integer userId) {
        List<Ratings> ratings = ratingRepository.findByRatedUserID_Id(userId);
        return ratings.stream().map(RatingDTO::new).collect(Collectors.toList());
    }

}
