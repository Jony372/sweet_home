package com.turing.sweet.home.modules.ratings.controller;

import com.turing.sweet.home.modules.ratings.dto.PublicRatingsByHouseDto;
import com.turing.sweet.home.modules.ratings.dto.RatingReqDto;
import com.turing.sweet.home.modules.ratings.service.RatingService;
import com.turing.sweet.home.modules.users.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/house/{houseId}")
    public ResponseEntity<Page<PublicRatingsByHouseDto>> getRatingsByHouseId(
            @PathVariable Long houseId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int elements,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection
    ){
        return ResponseEntity.ok(ratingService.getRatingsByHouseId(houseId, page, elements, sortBy, sortDirection));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicRatingsByHouseDto> updateRating(@PathVariable Long id, @RequestBody RatingReqDto ratingReqDto) {
        return ResponseEntity.ok(ratingService.updateRating(id, ratingReqDto));
    }

    @PostMapping
    public ResponseEntity<PublicRatingsByHouseDto> createRating(@RequestBody RatingReqDto ratingReqDto, @AuthenticationPrincipal UserEntity user) {
        return ResponseEntity.ok(ratingService.createRating(user, ratingReqDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id, @AuthenticationPrincipal UserEntity user) {
        ratingService.deleteRatingById(user.getUserId(), id);
        return ResponseEntity.noContent().build();
    }
}
