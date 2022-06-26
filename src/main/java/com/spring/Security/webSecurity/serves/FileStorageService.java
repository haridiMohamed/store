package com.spring.Security.webSecurity.serves;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import com.spring.Security.dao.ImgfileRepository;
import com.spring.Security.dao.ProductsRepository;
import com.spring.Security.entity.ImgFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Service
public class FileStorageService implements IspecimentSirvice {

    String relativeWebPath ="ecommerce/products"+ File.separator;
    String absoluteDiskPath =  System.getProperty("user.home")+File.separator+relativeWebPath;
    private final Path rootLocation = Paths.get("upload-dir"+File.separator+relativeWebPath+File.separator);

    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    ImgfileRepository imgfileRepository;

    @Autowired
    Environment environment;


    public void saveImage(MultipartFile[] images) throws IOException {
//        Products products = productsRepository.findByNmae2(image.getOriginalFilename());

        System.out.println("images"+images.length);
        for (MultipartFile image : images){
            ImgFile imgFile = new ImgFile();
            System.out.println("images"+image.getOriginalFilename());
             byte[] bytes= image.getBytes();
              Path path = Paths.get("upload-dir"+File.separator+relativeWebPath+File.separator+image.getOriginalFilename());
              Files.write(path, bytes);
            imgFile.setUrl("localhost:8080/api/findProduct/"+image.getOriginalFilename());
            imgFile.setName(image.getOriginalFilename());
            imgfileRepository.save(imgFile);
        }


      //  Path path = Paths.get(rootLocation+image.getOriginalFilename()+".jpg");
     //   Files.copy(image.getInputStream(), this.rootLocation.resolve(image.getOriginalFilename()+".jpg"));
//        imgFile.setProducts(products);
//        Set<ImgFile> imgFileSet = new HashSet<ImgFile>();
//        imgFileSet.add(imgFile);
//        products.setImgFiles(imgFileSet);
//        productsRepository.save(products);
//        return imgFileSet;
    }




//
//    public void saveImage(MultipartFile file) {
//        try {
//            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
//        } catch (Exception e) {
//            throw new RuntimeException("FAIL!");
//        }
//    }

//    public Resource loadFile(String filename) {
//        try {
//            System.out.println(filename);
//            Path file = rootLocation.resolve(filename+".jpg");
//            Resource resource = new UrlResource(file.toUri());
//            if (resource.exists() || resource.isReadable()) {
//                return resource;
//            } else {
//                throw new RuntimeException("FAIL!");
//            }
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("FAIL!");
//        }
//    }
//    public void deleteAll() {
//        FileSystemUtils.deleteRecursively(rootLocation.toFile());
//    }
//
//    public void init() {
//        try {
//            Files.createDirectory(rootLocation);
//        } catch (IOException e) {
//            throw new RuntimeException("Could not initialize storage!");
//        }
//    }

}