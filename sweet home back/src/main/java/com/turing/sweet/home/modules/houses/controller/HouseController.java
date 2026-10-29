package com.turing.sweet.home.modules.houses.controller;

import com.turing.sweet.home.modules.houses.dto.HouseReqDto;
import com.turing.sweet.home.modules.houses.dto.PublicHouseDto;
import com.turing.sweet.home.modules.houses.service.HouseService;
import com.turing.sweet.home.modules.houses.service.HouseServiceInterface;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/houses")
public class HouseController implements HouseServiceInterface {
    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @Override
    @PostMapping
    public PublicHouseDto createHouse(@RequestBody HouseReqDto houseReqDto) {
        System.out.println("HouseController.createHouse: " + houseReqDto);
        return this.houseService.createHouse(houseReqDto);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteHouseById(@PathVariable Long id) {
        this.houseService.deleteHouseById(id);
    }

    @Override
    @GetMapping
    public Page<PublicHouseDto> getAllHouses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int elements,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        return this.houseService.getAllHouses(page, elements, sortBy, sortDirection);
    }

    @Override
    @GetMapping("/category/{categoryId}")
    public Page<PublicHouseDto> getHousesByCategoryId(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int elements,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        return this.houseService.getHousesByCategoryId(categoryId, page, elements, sortBy, sortDirection);
    }

    @Override
    @GetMapping("/name/{name}")
    public Page<PublicHouseDto> getHousesByNameLike(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int elements,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        return this.houseService.getHousesByNameLike(name, page, elements, sortBy, sortDirection);
    }

    @Override
    @GetMapping("/{id}")
    public PublicHouseDto getPublicHouseById(@PathVariable Long id) {
        return this.houseService.getPublicHouseById(id);
    }

    @Override
    @PutMapping("/{id}")
    public PublicHouseDto updateHouse(
            @PathVariable Long id,
            @RequestBody HouseReqDto houseReqDto
    ) {
        return this.houseService.updateHouse(id, houseReqDto);
    }
}
