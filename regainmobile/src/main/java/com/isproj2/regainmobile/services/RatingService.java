package com.isproj2.regainmobile.services;

import java.util.List;

import com.isproj2.regainmobile.dto.RatingDTO;

public interface RatingService {
    RatingDTO addRating(RatingDTO ratingDTO);
    RatingDTO updateComment(Integer ratingId, String newComment);
    List<RatingDTO> getRatingsByUserId(Integer userId);
    List<RatingDTO> getRatingsGivenByUser(Integer userId);
}
