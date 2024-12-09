package com.isproj2.regainmobile.dto;

import java.time.LocalDateTime;

import com.isproj2.regainmobile.model.Ratings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {

    private Integer ratingId;
    private Integer ratedUserId;
    private Integer ratedByUserId;
    private Integer rateValue;
    private String comments;
    private LocalDateTime dateCreated;
    private LocalDateTime dateEdited;

    public RatingDTO(Ratings rating) {
        this.ratingId = rating.getRatingID();
        this.ratedUserId = rating.getRatedUserID().getUserID();
        this.ratedByUserId = rating.getRatedByUserID().getUserID();
        this.rateValue = rating.getRateValue();
        this.comments = rating.getComments();
        this.dateCreated = rating.getDateCreated();
        this.dateEdited = rating.getDateEdited();
    }
}
