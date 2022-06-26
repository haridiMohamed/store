package com.spring.Security.dao;

import com.spring.Security.entity.CategoryProduct;
import com.spring.Security.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Products, String> {
//    List<Products> findByPublished(boolean published);
//       List<Products> findByName(String product);

    @Query("select c from Products c where c.refProduct like :x")
    Products findByNmae2(@Param("x")String refProduct);

    @Query("select c from Products c where c.categore.id like :x")
    List <Products> findByNmae(@Param("x")Long category);
}
