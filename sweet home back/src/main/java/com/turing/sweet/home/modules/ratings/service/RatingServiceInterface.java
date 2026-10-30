package com.turing.sweet.home.modules.ratings.service;

import com.turing.sweet.home.modules.ratings.dto.PublicRatingDto;
import com.turing.sweet.home.modules.ratings.dto.PublicRatingsByHouseDto;
import com.turing.sweet.home.modules.ratings.dto.RatingReqDto;
import com.turing.sweet.home.modules.users.entity.UserEntity;
import org.springframework.data.domain.Page;

public interface RatingServiceInterface {
    Page<PublicRatingsByHouseDto> getRatingsByHouseId(Long houseId, int page, int elements, String sortBy, String sortDirection);
    PublicRatingsByHouseDto updateRating(Long userId, RatingReqDto ratingReqDto );
    PublicRatingsByHouseDto createRating(UserEntity user, RatingReqDto ratingReqDto);
    Page<PublicRatingDto> getAllRatings(int page, int elements);
    void deleteRatingById(Long userId, Long id);
}
