package com.spring.Security.dao;

import com.spring.Security.entity.ImgFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImgfileRepository extends JpaRepository<ImgFile, Long> {
//    @Query("select c from ImgFile c where c.products.refProduct like :x")
//    List <ImgFile> findByName(@Param("x")String imageName);
////    Optional<ImgFile> findByName(@Param("x")String imageName);

}
