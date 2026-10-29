package com.turing.sweet.home.modules.cateogories.controller;

import com.turing.sweet.home.modules.cateogories.dto.CategoryReqDto;
import com.turing.sweet.home.modules.cateogories.dto.PublicCategoryDto;
import com.turing.sweet.home.modules.cateogories.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Page<PublicCategoryDto>> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int elements,
            @RequestParam(defaultValue = "Name") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection
    ){
        Page<PublicCategoryDto> categories = this.categoryService.getAllCategories(page, elements, sortBy, sortDirection);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicCategoryDto> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(this.categoryService.getPublicCategoryById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PublicCategoryDto> getCategoryByName(@PathVariable String name){
        return ResponseEntity.ok(this.categoryService.getPublicCategoryByName(name));
    }

    @PostMapping
    public ResponseEntity<PublicCategoryDto> createCategory(@RequestBody CategoryReqDto categoryReqDto){
        return ResponseEntity.ok(this.categoryService.createCategory(categoryReqDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicCategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryReqDto categoryReqDto){
        return ResponseEntity.ok(this.categoryService.updateCategory(id, categoryReqDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id){
        this.categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}
