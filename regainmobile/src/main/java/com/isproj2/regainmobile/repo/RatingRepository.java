package com.isproj2.regainmobile.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isproj2.regainmobile.model.Ratings;

@Repository
public interface RatingRepository extends JpaRepository<Ratings, Integer> {
    List<Ratings> findByRatedUserID_UserID(Integer ratedUserId);
}
