package com.turing.sweet.home.modules.cateogories.entity;

import com.turing.sweet.home.audit.AuditableEntity;
import com.turing.sweet.home.modules.houses.entity.HouseEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long categoryId;

    @Column(unique = true)
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HouseEntity> houses;

    public CategoryEntity() {
    }

    public CategoryEntity(String name, String description) {
        this.description = description;
        this.name = name;
    }

    @Override
    public void delete() {
        super.delete();
        this.setName(null);
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
