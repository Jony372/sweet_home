package com.turing.sweet.home.modules.houses.service;

import com.turing.sweet.home.exceptions.ResourceNotFoundException;
import com.turing.sweet.home.modules.cateogories.entity.CategoryEntity;
import com.turing.sweet.home.modules.cateogories.service.CategoryService;
import com.turing.sweet.home.modules.houses.dto.HouseReqDto;
import com.turing.sweet.home.modules.houses.dto.PublicHouseDto;
import com.turing.sweet.home.modules.houses.entity.HouseEntity;
import com.turing.sweet.home.modules.houses.mapper.HouseMapper;
import com.turing.sweet.home.modules.houses.repository.HouseRepository;
import com.turing.sweet.home.modules.ratings.service.RatingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HouseService implements HouseServiceInterface {
    private final HouseRepository houseRepository;
    private final CategoryService categoryService;
    private final RatingService ratingService;

    public HouseService(
            HouseRepository houseRepository,
            CategoryService categoryService,
            RatingService ratingService
    ) {
        this.houseRepository = houseRepository;
        this.categoryService = categoryService;
        this.ratingService = ratingService;
    }

    public HouseEntity getHouseById(Long id) {
        return this.houseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro la casa"));
    }

    @Transactional
    @Override
    public PublicHouseDto createHouse(HouseReqDto houseReqDto) {
        System.out.println("HouseService.createHouse: " + houseReqDto);
        CategoryEntity category = categoryService.getCategoryById(houseReqDto.categoryId());
        System.out.println("HouseService.createHouse: category = " + category);
        HouseEntity house = new HouseEntity(
                category,
                houseReqDto.description(),
                houseReqDto.imgUrl(),
                houseReqDto.name(),
                houseReqDto.price()
        );
        System.out.println("HouseService.createHouse: house = " + house);
        return HouseMapper.toPublicHouseDto(this.houseRepository.save(house), 0.0);
    }

    @Transactional
    @Override
    public void deleteHouseById(Long id) {
        HouseEntity house = getHouseById(id);
        house.delete();
    }

    @Override
    public Page<PublicHouseDto> getAllHouses(int page, int elements, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, elements, sort);
        Page<HouseEntity> houseEntities = this.houseRepository.getAllByDeletedAtIsNull(pageable);
        return houseEntities.map(house -> HouseMapper.toPublicHouseDto(house, this.ratingService.getRatingByHouseId(house.getHouseId())));
    }

    @Override
    public PublicHouseDto getPublicHouseById(Long id) {
        HouseEntity house = this.getHouseById(id);
        Double rating = this.ratingService.getRatingByHouseId(house.getHouseId());
        return HouseMapper.toPublicHouseDto(house, rating);
    }

    @Override
    public Page<PublicHouseDto> getHousesByCategoryId(Long categoryId, int page, int elements, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, elements, sort);
        Page<HouseEntity> houseEntities = this.houseRepository.getAllByCategory_CategoryIdAndDeletedAtIsNull(categoryId, pageable);
        return houseEntities.map(house -> HouseMapper.toPublicHouseDto(house, this.ratingService.getRatingByHouseId(house.getHouseId())));
    }

    @Override
    public Page<PublicHouseDto> getHousesByNameLike(String name, int page, int elements, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, elements, sort);
        Page<HouseEntity> houseEntities = this.houseRepository.findAllByNameContainingIgnoreCaseAndDeletedAtIsNull(name, pageable);
        return houseEntities.map(house -> HouseMapper.toPublicHouseDto(house, this.ratingService.getRatingByHouseId(house.getHouseId())));
    }

    @Transactional
    @Override
    public PublicHouseDto updateHouse(Long id, HouseReqDto houseReqDto) {
        HouseEntity house = this.getHouseById(id);
        house.setName(houseReqDto.name());
        house.setDescription(houseReqDto.description());
        house.setImgUrl(houseReqDto.imgUrl());
        house.setPrice(houseReqDto.price());
        Double rating = this.ratingService.getRatingByHouseId(house.getHouseId());
        return HouseMapper.toPublicHouseDto(this.houseRepository.save(house), rating);
    }
}
