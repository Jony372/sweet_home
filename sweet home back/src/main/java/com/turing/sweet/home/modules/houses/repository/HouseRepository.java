package com.turing.sweet.home.modules.houses.repository;

import com.turing.sweet.home.modules.houses.entity.HouseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<HouseEntity, Long> {
    Page<HouseEntity> getAllByDeletedAtIsNull(Pageable pageable);
    Page<HouseEntity> getAllByCategory_CategoryIdAndDeletedAtIsNull(Long categoryId, Pageable pageable);
    Page<HouseEntity> findAllByNameContainingIgnoreCaseAndDeletedAtIsNull(String name, Pageable pageable);
}
