package com.cg.movierentalapp.enitites;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", columnDefinition = "TINYINT")
    private Byte categoryId;

    @Column(name = "name", nullable = false)
    @NotEmpty(message = "Category name is required")
    @Size(max = 50, message = "Category name cannot exceed 50 characters")
    private String name;

    @Column(name = "last_update", nullable = false)
    @NotNull(message = "Last update timestamp is required")
    private Timestamp lastUpdate;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Film> films = new HashSet<>();

    public Category(Byte categoryId, String name, Timestamp lastUpdate) {
        super();
        this.categoryId = categoryId;
        this.name = name;
        this.lastUpdate = lastUpdate;
    }

    public Category() {
        super();
    }

    public Byte getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Byte categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Category [categoryId=" + categoryId + ", name=" + name + ", lastUpdate=" + lastUpdate + "]";
    }

    // Getters and setters
}
