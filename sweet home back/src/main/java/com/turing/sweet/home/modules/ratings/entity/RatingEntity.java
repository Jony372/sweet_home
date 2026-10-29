package com.turing.sweet.home.modules.ratings.entity;

import com.turing.sweet.home.audit.AuditableEntity;
import com.turing.sweet.home.modules.houses.entity.HouseEntity;
import com.turing.sweet.home.modules.users.entity.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "ratings")
public class RatingEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long ratingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id", referencedColumnName = "house_id", nullable = false)
    private HouseEntity house;

    @Column(nullable = false)
    @Min(value = 1, message = "La calificación debe ser minimo de 1")
    @Max(value = 5, message = "La calificación debe ser máximo de 5")
    private Integer rating;

    @Column(length = 500)
    private String comment;

    public RatingEntity() {
    }

    public RatingEntity(String comment, HouseEntity house, Integer rating, UserEntity user) {
        this.comment = comment;
        this.house = house;
        this.rating = rating;
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public HouseEntity getHouse() {
        return house;
    }

    public void setHouse(HouseEntity house) {
        this.house = house;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
