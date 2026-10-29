package com.turing.sweet.home.modules.cateogories.repository;

import com.turing.sweet.home.modules.cateogories.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Page<CategoryEntity> findAllByDeletedAtIsNull(Pageable pageable);
    Optional<CategoryEntity> findByName(String name);
    Boolean existsByName(String name);
}
