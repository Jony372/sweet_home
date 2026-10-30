package com.turing.sweet.home.modules.ratings.mapper;

import com.turing.sweet.home.modules.ratings.dto.PublicRatingDto;
import com.turing.sweet.home.modules.ratings.dto.PublicRatingsByHouseDto;
import com.turing.sweet.home.modules.ratings.entity.RatingEntity;

public class RatingMapper {
    public static PublicRatingsByHouseDto toPublicRatingsByHouseDto(RatingEntity ratingEntity) {
        if (ratingEntity == null) return null;
        return new PublicRatingsByHouseDto(
                ratingEntity.getRatingId(),
                ratingEntity.getUser().getUsername(),
                ratingEntity.getRating(),
                ratingEntity.getComment(),
                ratingEntity.getCreatedAt(),
                ratingEntity.getUpdatedAt()
        );
    }

    public static PublicRatingDto toPublicRatingDto(RatingEntity ratingEntity) {
        if (ratingEntity == null) return null;
        return new PublicRatingDto(
                ratingEntity.getRatingId(),
                ratingEntity.getUser().getUsername(),
                ratingEntity.getHouse().getName(),
                ratingEntity.getHouse().getImgUrl(),
                ratingEntity.getRating(),
                ratingEntity.getComment()
        );
    }
}
