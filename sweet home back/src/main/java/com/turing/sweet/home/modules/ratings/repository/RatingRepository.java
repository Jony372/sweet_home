package com.turing.sweet.home.modules.ratings.repository;

import com.turing.sweet.home.modules.ratings.entity.RatingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<RatingEntity, Long> {
    Optional<RatingEntity> findByUser_UserIdAndHouse_HouseId(Long userId, Long houseId);
    Page<RatingEntity> findByHouse_HouseId(Long houseId, Pageable pageable);
    List<RatingEntity> findByHouse_HouseId(Long houseId);
}
