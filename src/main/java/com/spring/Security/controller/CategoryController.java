package com.spring.Security.controller;

import com.spring.Security.dao.CategoryRepository;
import com.spring.Security.entity.CategoryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/category")
    public ResponseEntity<CategoryProduct> createCategory(@RequestBody CategoryProduct category) {
        try {
            CategoryProduct save = categoryRepository
                    .save(new CategoryProduct(category.getNameCategory()));
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/category")
    public ResponseEntity<List<CategoryProduct>> getAllCategory(@RequestParam(required = false) String category) {
        try {
            List<CategoryProduct> categoryProducts = new ArrayList<CategoryProduct>();
            if (category == null)
                categoryRepository.findAll().forEach(categoryProducts::add);

//            else
//                categoryRepository.findByNmae(category).forEach(categoryProducts::add);
            if (categoryProducts.isEmpty()) {
                System.out.println("/isEmpty");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            System.out.println("/category");
            return new ResponseEntity<>(categoryProducts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category?nameCategor={nameCategor}")
    public ResponseEntity<CategoryProduct> getCategoryByName(@PathVariable("nameCategory") String nameCategory) {
        Optional<CategoryProduct> categorylData = categoryRepository.findByNmae(nameCategory);
        System.out.println("/category/{nameCategory}");
        if (categorylData.isPresent()) {
            return new ResponseEntity<>(categorylData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PutMapping("/category/{nameCategory}")
//    public ResponseEntity<CategoryProduct> updateCategory(@PathVariable("nameCategory") String nameCategory, @RequestBody CategoryProduct category) {
//        Optional<CategoryProduct> tutorialData = categoryRepository.findByNmae(nameCategory);
//        if (tutorialData.isPresent()) {
//            CategoryProduct _category = tutorialData.get();
//            _category.setNameCategory(category.getNameCategory());
//            return new ResponseEntity<>(categoryRepository.save(_category), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

//    @DeleteMapping("/category/{id}")
//    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") String id) {
//        try {
//            categoryRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @DeleteMapping("/deleteAllProduct")
//    public ResponseEntity<HttpStatus> deleteAllTutorials() {
//        try {
//            categoryRepository.deleteAll();
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
