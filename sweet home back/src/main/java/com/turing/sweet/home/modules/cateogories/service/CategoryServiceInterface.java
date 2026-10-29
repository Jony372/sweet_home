package com.turing.sweet.home.modules.cateogories.service;

import com.turing.sweet.home.modules.cateogories.dto.CategoryReqDto;
import com.turing.sweet.home.modules.cateogories.dto.PublicCategoryDto;
import com.turing.sweet.home.modules.cateogories.entity.CategoryEntity;
import org.springframework.data.domain.Page;

public interface CategoryServiceInterface {
    Page<PublicCategoryDto> getAllCategories(int page, int elements, String sortBy, String sortDirection);
    PublicCategoryDto getPublicCategoryById(Long id);
    PublicCategoryDto getPublicCategoryByName(String name);
    PublicCategoryDto createCategory(CategoryReqDto categoryReqDto);
    PublicCategoryDto updateCategory(Long id, CategoryReqDto categoryReqDto);
    void deleteCategoryById(Long id);
}
