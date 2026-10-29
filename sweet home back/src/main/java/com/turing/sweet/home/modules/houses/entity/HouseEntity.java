package com.turing.sweet.home.modules.houses.entity;

import com.turing.sweet.home.audit.AuditableEntity;
import com.turing.sweet.home.modules.cateogories.entity.CategoryEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "houses")
public class HouseEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private Long houseId;

    @Column
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name = "img_url", nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
    private CategoryEntity category;

    public HouseEntity() {
    }

    public HouseEntity(CategoryEntity category, String description, String imgUrl, String name, BigDecimal price) {
        this.category = category;
        this.description = description;
        this.imgUrl = imgUrl;
        this.name = name;
        this.price = price;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
