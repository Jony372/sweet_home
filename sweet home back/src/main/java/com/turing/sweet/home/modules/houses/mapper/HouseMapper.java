package com.turing.sweet.home.modules.houses.mapper;

import com.turing.sweet.home.modules.houses.dto.PublicHouseDto;
import com.turing.sweet.home.modules.houses.entity.HouseEntity;

public class HouseMapper {
    public static PublicHouseDto toPublicHouseDto(HouseEntity houseEntity, Double rating) {
        return new PublicHouseDto(
                houseEntity.getHouseId(),
                houseEntity.getName(),
                houseEntity.getDescription(),
                houseEntity.getImgUrl(),
                houseEntity.getPrice(),
                houseEntity.getCategory().getName(),
                rating,
                houseEntity.getCreatedAt(),
                houseEntity.getUpdatedAt()
        );
    }
}
