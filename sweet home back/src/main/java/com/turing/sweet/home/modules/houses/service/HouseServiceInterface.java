package com.turing.sweet.home.modules.houses.service;

import com.turing.sweet.home.modules.houses.dto.HouseReqDto;
import com.turing.sweet.home.modules.houses.dto.PublicHouseDto;
import org.springframework.data.domain.Page;

public interface HouseServiceInterface {
    public Page<PublicHouseDto> getAllHouses(int page, int elements, String sortBy, String sortDirection);
    public PublicHouseDto getPublicHouseById(Long id);
    public Page<PublicHouseDto> getHousesByCategoryId(Long categoryId, int page, int elements, String sortBy, String sortDirection);
    public Page<PublicHouseDto> getHousesByNameLike(String name, int page, int elements, String sortBy, String sortDirection);
    public PublicHouseDto createHouse(HouseReqDto houseReqDto);
    public PublicHouseDto updateHouse(Long id, HouseReqDto houseReqDto);
    public void deleteHouseById(Long id);

}
