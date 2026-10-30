package com.turing.sweet.home.modules.cateogories.service;

import com.turing.sweet.home.exceptions.ResourceNotFoundException;
import com.turing.sweet.home.modules.cateogories.dto.CategoryReqDto;
import com.turing.sweet.home.modules.cateogories.dto.PublicCategoryDto;
import com.turing.sweet.home.modules.cateogories.entity.CategoryEntity;
import com.turing.sweet.home.modules.cateogories.mapper.CategoryMapper;
import com.turing.sweet.home.modules.cateogories.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService implements CategoryServiceInterface {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryEntity getCategoryById(Long id){
        return this.categoryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Categoria con el no encontrada"));
    }

    public CategoryEntity getCategoryByName(String name){
        return this.categoryRepository.findByName(name)
            .orElseThrow(() -> new ResourceNotFoundException("Categoria con el nombre %s no encontrada".formatted(name)));
    }

    @Override
    public List<PublicCategoryDto> getAllCategories() {
        return this.categoryRepository.findAllByDeletedAtIsNull().stream()
            .map(CategoryMapper::toPublicCategoryDto)
            .toList();
    }

    @Override
    public PublicCategoryDto getPublicCategoryById(Long id) {
        return CategoryMapper.toPublicCategoryDto(getCategoryById(id));
    }

    @Override
    public PublicCategoryDto getPublicCategoryByName(String name) {
        return CategoryMapper.toPublicCategoryDto(getCategoryByName(name));
    }

    @Transactional
    @Override
    public PublicCategoryDto createCategory(CategoryReqDto categoryReqDto) {
        CategoryEntity category = new CategoryEntity(categoryReqDto.name(), categoryReqDto.description());
        return CategoryMapper.toPublicCategoryDto(this.categoryRepository.save(category));
    }

    @Override
    public PublicCategoryDto updateCategory(Long id, CategoryReqDto categoryReqDto) {
        CategoryEntity category = getCategoryById(id);
        category.setName(categoryReqDto.name());
        category.setDescription(categoryReqDto.description());
        return CategoryMapper.toPublicCategoryDto(this.categoryRepository.save(category));
    }

    @Override
    public void deleteCategoryById(Long id) {
        CategoryEntity category = getCategoryById(id);
        category.delete();
        this.categoryRepository.save(category);
    }
}
