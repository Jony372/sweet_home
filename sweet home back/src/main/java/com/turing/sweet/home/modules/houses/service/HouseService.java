package com.turing.sweet.home.modules.houses.service;

import com.turing.sweet.home.exceptions.ResourceNotFoundException;
import com.turing.sweet.home.modules.cateogories.entity.CategoryEntity;
import com.turing.sweet.home.modules.cateogories.service.CategoryService;
import com.turing.sweet.home.modules.houses.dto.HouseReqDto;
import com.turing.sweet.home.modules.houses.dto.PublicHouseDto;
import com.turing.sweet.home.modules.houses.entity.HouseEntity;
import com.turing.sweet.home.modules.houses.mapper.HouseMapper;
import com.turing.sweet.home.modules.houses.repository.HouseRepository;
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

    public HouseService(HouseRepository houseRepository, CategoryService categoryService) {
        this.houseRepository = houseRepository;
        this.categoryService = categoryService;
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
        return HouseMapper.toPublicHouseDto(this.houseRepository.save(house));
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
        return houseEntities.map(HouseMapper::toPublicHouseDto);
    }

    @Override
    public PublicHouseDto getPublicHouseById(Long id) {
        return HouseMapper.toPublicHouseDto(getHouseById(id));
    }

    @Override
    public Page<PublicHouseDto> getHousesByCategoryId(Long categoryId, int page, int elements, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, elements, sort);
        Page<HouseEntity> houseEntities = this.houseRepository.getAllByCategory_CategoryIdAndDeletedAtIsNull(categoryId, pageable);
        return houseEntities.map(HouseMapper::toPublicHouseDto);
    }

    @Override
    public Page<PublicHouseDto> getHousesByNameLike(String name, int page, int elements, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, elements, sort);
        Page<HouseEntity> houseEntities = this.houseRepository.findAllByNameContainingIgnoreCaseAndDeletedAtIsNull(name, pageable);
        return houseEntities.map(HouseMapper::toPublicHouseDto);
    }

    @Transactional
    @Override
    public PublicHouseDto updateHouse(Long id, HouseReqDto houseReqDto) {
        HouseEntity house = this.getHouseById(id);
        house.setName(houseReqDto.name());
        house.setDescription(houseReqDto.description());
        house.setImgUrl(houseReqDto.imgUrl());
        house.setPrice(houseReqDto.price());
        return HouseMapper.toPublicHouseDto(this.houseRepository.save(house));
    }
}
