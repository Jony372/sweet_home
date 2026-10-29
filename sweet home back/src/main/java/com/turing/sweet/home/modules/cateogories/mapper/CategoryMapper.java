package com.turing.sweet.home.modules.cateogories.mapper;

import com.turing.sweet.home.modules.cateogories.dto.PublicCategoryDto;
import com.turing.sweet.home.modules.cateogories.entity.CategoryEntity;

public class CategoryMapper {
    public static PublicCategoryDto toPublicCategoryDto(CategoryEntity categoryEntity) {
        if(categoryEntity == null) return null;
        return new PublicCategoryDto(
                categoryEntity.getCategoryId(),
                categoryEntity.getName(),
                categoryEntity.getDescription()
        );
    }
}
