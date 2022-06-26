package com.spring.Security.controller;

import com.spring.Security.entity.ImgFile;
import com.spring.Security.webSecurity.serves.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    FileStorageService fileStorageService;

    String relativeWebPath ="ecommerce/products"+ File.separator;
    String absoluteDiskPath =  System.getProperty("user.home")+File.separator+relativeWebPath;
    private final Path rootLocation = Paths.get("upload-dir"+File.separator+relativeWebPath+File.separator);





    @PostMapping(value = "/saveImg", consumes = { "multipart/form-data" } )
    public ResponseEntity<List<ImgFile>> createImg(
            @RequestParam("file") MultipartFile[] image) throws IOException {
        fileStorageService.saveImage(image);
      //  System.out.println(image.length);
        return new ResponseEntity<List<ImgFile>>(HttpStatus.CREATED);
    }





//    @ResponseBody
//    @GetMapping(value="/findProduct/{refProduct}",
//            produces= MediaType.IMAGE_JPEG_VALUE)
//    public byte[] getPhotp(@PathVariable("refProduct") String refProduct) throws Exception {
//
//        File f = new File("upload-dir"+File.separator+relativeWebPath+File.separator+refProduc);
//
//        return IOUtils.toByteArray(new FileInputStream((f)));
//    }
}
