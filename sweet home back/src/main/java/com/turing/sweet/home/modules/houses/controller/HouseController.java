package com.turing.sweet.home.modules.houses.controller;

import com.turing.sweet.home.modules.houses.dto.HouseReqDto;
import com.turing.sweet.home.modules.houses.dto.PublicHouseDto;
import com.turing.sweet.home.modules.houses.service.HouseService;
import com.turing.sweet.home.modules.houses.service.HouseServiceInterface;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/houses")
public class HouseController {
    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping
    public ResponseEntity<PublicHouseDto> createHouse(@RequestBody HouseReqDto houseReqDto) {
        System.out.println("HouseController.createHouse: " + houseReqDto);
        return ResponseEntity.ok(this.houseService.createHouse(houseReqDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHouseById(@PathVariable Long id) {
        this.houseService.deleteHouseById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<PublicHouseDto>> getAllHouses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int elements,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Page<PublicHouseDto> houses = this.houseService.getAllHouses(page, elements, sortBy, sortDirection);
        return ResponseEntity.ok(houses);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<PublicHouseDto>> getHousesByCategoryId(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int elements,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Page<PublicHouseDto> houses = this.houseService.getHousesByCategoryId(categoryId, page, elements, sortBy, sortDirection);
        return ResponseEntity.ok(houses);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Page<PublicHouseDto>> getHousesByNameLike(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int elements,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Page<PublicHouseDto> houses = this.houseService.getHousesByNameLike(name, page, elements, sortBy, sortDirection);
        return ResponseEntity.ok(houses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicHouseDto> getPublicHouseById(@PathVariable Long id) {
        return ResponseEntity.ok(this.houseService.getPublicHouseById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicHouseDto> updateHouse(
            @PathVariable Long id,
            @RequestBody HouseReqDto houseReqDto
    ) {
        return ResponseEntity.ok(this.houseService.updateHouse(id, houseReqDto));
    }
}
