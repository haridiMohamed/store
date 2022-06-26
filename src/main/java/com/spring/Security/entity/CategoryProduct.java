package com.spring.Security.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "category")
@Data
public class CategoryProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    private String nameCategory;

    public CategoryProduct() {
    }

    public CategoryProduct(@NotNull String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
