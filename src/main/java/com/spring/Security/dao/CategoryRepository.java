package com.spring.Security.dao;

import com.spring.Security.entity.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryProduct, String> {
     @Query("select c from CategoryProduct c where c.nameCategory like :x")
    Optional<CategoryProduct> findByNmae(@Param("x")String nameCategory);

    @Query("select c from CategoryProduct c where c.nameCategory like :x")
    CategoryProduct findByNmae2(@Param("x")String nameCategory);



}
