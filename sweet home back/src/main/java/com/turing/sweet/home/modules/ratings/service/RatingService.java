package com.turing.sweet.home.modules.ratings.service;

import com.turing.sweet.home.exceptions.BadRequestException;
import com.turing.sweet.home.exceptions.ResourceNotFoundException;
import com.turing.sweet.home.modules.houses.entity.HouseEntity;
import com.turing.sweet.home.modules.houses.repository.HouseRepository;
import com.turing.sweet.home.modules.houses.service.HouseService;
import com.turing.sweet.home.modules.ratings.dto.PublicRatingDto;
import com.turing.sweet.home.modules.ratings.dto.PublicRatingsByHouseDto;
import com.turing.sweet.home.modules.ratings.dto.RatingReqDto;
import com.turing.sweet.home.modules.ratings.entity.RatingEntity;
import com.turing.sweet.home.modules.ratings.mapper.RatingMapper;
import com.turing.sweet.home.modules.ratings.repository.RatingRepository;
import com.turing.sweet.home.modules.users.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService implements RatingServiceInterface {
    private final RatingRepository ratingRepository;
    private final HouseRepository houseRepository;

    public RatingService(RatingRepository ratingRepository, HouseRepository houseRepository) {
        this.ratingRepository = ratingRepository;
        this.houseRepository = houseRepository;
    }

    public double getRatingByHouseId(Long houseId) {
        return this.ratingRepository.findByHouse_HouseId(houseId)
                .stream()
                .mapToDouble(RatingEntity::getRating)
                .average()
                .orElse(0.0);
    }

    public RatingEntity getRatingById(Long id) {
        return this.ratingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontró la calificación"));
    }

    @Override
    public Page<PublicRatingsByHouseDto> getRatingsByHouseId(Long houseId, int page, int elements, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, elements, sort);
        return this.ratingRepository.findByHouse_HouseId(houseId, pageable)
                .map(RatingMapper::toPublicRatingsByHouseDto);
    }

    @Override
    public PublicRatingsByHouseDto updateRating(Long userId, RatingReqDto ratingReqDto) {
        RatingEntity rating = getRatingById(ratingReqDto.houseId());
        if (!rating.getUser().getUserId().equals(userId)) {
            throw new BadRequestException("No puedes modificar una calificación que no es tuya");
        }
        rating.setRating(ratingReqDto.rating());
        rating.setComment(ratingReqDto.comment());
        return RatingMapper.toPublicRatingsByHouseDto(this.ratingRepository.save(rating));
    }

    @Override
    public PublicRatingsByHouseDto createRating(UserEntity user, RatingReqDto ratingReqDto) {
        HouseEntity house = houseRepository.findById(ratingReqDto.houseId()).orElseThrow(() -> new ResourceNotFoundException("No se encontró la casa"));
        RatingEntity ratingEntity = new RatingEntity(ratingReqDto.comment(), house, ratingReqDto.rating(), user);
        return RatingMapper.toPublicRatingsByHouseDto(this.ratingRepository.save(ratingEntity));
    }

    @Override
    public void deleteRatingById(Long userId, Long id) {
        RatingEntity rating = getRatingById(id);
        if (!rating.getUser().getUserId().equals(userId)) {
            throw new BadRequestException("No puedes eliminar una calificación que no es tuya");
        }
        this.ratingRepository.deleteById(id);
    }

    @Override
    public Page<PublicRatingDto> getAllRatings(int page, int elements) {
        Pageable pageable = PageRequest.of(page, elements);
        return this.ratingRepository.findAll(pageable)
                .map(RatingMapper::toPublicRatingDto);
    }
}
