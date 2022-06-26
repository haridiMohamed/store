package com.spring.Security.controller;
import com.spring.Security.dao.CategoryRepository;
import com.spring.Security.dao.ImgfileRepository;
import com.spring.Security.dao.ProductsRepository;
import com.spring.Security.entity.CategoryProduct;
import com.spring.Security.entity.ImgFile;
import com.spring.Security.entity.Products;
import com.spring.Security.webSecurity.serves.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProductsControl  {
    String relativeWebPath ="ecommerce/products"+ File.separator;

    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ImgfileRepository imgfileRepository;

    @PostMapping(value = "/saveProduct", consumes = { "multipart/form-data" } )
public ResponseEntity<List<?>>  createCategory(@RequestParam("file") MultipartFile[] images,
                                        @RequestParam("refProduct") String refProduct,
                                        @RequestParam("nameProduct") String nameProduct,
                                        @RequestParam("descProduct") String  descProduct,
                                        @RequestParam("totalQte") int  totalQte,
                                        @RequestParam("prix") Double  prix,
                                        @RequestParam ("nameCategory") String nameCategory


    ) throws IOException {
    try {
        System.out.println("nameCategory"+nameCategory);
        System.out.println("images.length"+images.length);
        CategoryProduct categoryProduct = categoryRepository.findByNmae2(nameCategory);
        if(categoryProduct != null){
            Products save = new Products();
            Set<ImgFile> imgFileSet = new HashSet<ImgFile>();
            for(MultipartFile image : images){
                ImgFile imgFile = new ImgFile();
                byte[] bytes= image.getBytes();
                Path path = Paths.get("upload-dir"+ File.separator+relativeWebPath+File.separator+image.getOriginalFilename());
                Files.write(path, bytes);
                imgFile.setUrl("localhost:8080/api/findProduct/"+image.getOriginalFilename());
                imgFile.setName(image.getOriginalFilename());
               imgFileSet.add(imgFile);

                imgfileRepository.save(imgFile);
            }
           save = productsRepository
                    .save(new Products(refProduct,nameProduct,
                            descProduct,totalQte,prix
                            ,categoryProduct,imgFileSet));
            return new ResponseEntity(save, HttpStatus.CREATED);
        }else
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
    @GetMapping("/listProducts")
    public ResponseEntity<List<Products>> getAllProducts(@RequestParam(required = false) String category) {
        try {
            List<Products> products = new ArrayList<Products>();
            if (category == null)
                productsRepository.findAll().forEach(products::add);
            if (products.isEmpty()) {
                System.out.println("/isEmpty");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            System.out.println("/products");
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("/INTERNAL_SERVER_ERROR");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findCatProduct/{categore}")
    public ResponseEntity<List<Products>> getProductByCategory(@PathVariable("categore") Long category) {
        System.out.println(category);
        try {
            List<Products> products = new ArrayList<Products>();
            if (category != null)
                products =    productsRepository.findByNmae(category);
            if (products.isEmpty()) {
                System.out.println("/isEmpty");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            System.out.println("/products");
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("/INTERNAL_SERVER_ERROR");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






//    List<String> files = new ArrayList<String>();

//    @PostMapping("/post")
//    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
//        String message = "";
//        try {
//            fileStorageService.saveImage(file);
//            files.add(file.getOriginalFilename());
//
//            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
//            return ResponseEntity.status(HttpStatus.OK).body(message);
//        } catch (Exception e) {
//            message = "FAIL to upload " + file.getOriginalFilename() + "!";
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
//        }
//    }
//    @GetMapping("/getallfiles")
//    public ResponseEntity<List<String>> getListFiles(Model model) {
//        List<String> fileNames = files
//                .stream().map(fileName -> MvcUriComponentsBuilder
//                        .fromMethodName(ProductsControl.class, "getFile", fileName).build().toString())
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok().body(fileNames);
//    }
//    @GetMapping("/files/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
//        Resource file = fileStorageService.loadFile(filename);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
//                .body(file);
//    }





    //    @RequestMapping(value="/getPhoto",
//            produces=MediaType.IMAGE_JPEG_VALUE)
//    @ResponseBody
//    @GetMapping(value = "/findProduct/{refProduct}",produces=MediaType.IMAGE_JPEG_VALUE)
//    public byte[] getTutorialById(@PathVariable("refProduct") String refProduct)throws Exception {
//        File f = new File(absoluteDiskPath+refProduct+".jpg");
////        imgFiles=  imgfileRepository.findByName(refProduct);
////        if (!imgFiles.isEmpty()) {
////            return new ResponseEntity(imgFiles, HttpStatus.OK);
//            return IOUtils.toByteArray(new FileInputStream(f));
//
//
//        //else {
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }



            // =====  save Image with ref product ====//




//    @GetMapping("/findProduct/{refProduct}")
//    @ResponseBody
//    public ResponseEntity<Resource> getFile(@PathVariable("refProduct") String filename) {
//        System.out.println(filename + "for get image");
//        Resource file = fileStorageService.loadFile(filename);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; refProduct=\"" + file.getFilename() + "\"")
//                .body(file);
//    }
//


//    @GetMapping("/findProduct/{refProduct}")
//    public ResponseEntity<ImgFile> getCategoryByName(@PathVariable("refProduct") String nameCategory) {
//        List <ImgFile> imgFiles = imgfileRepository.findByName(nameCategory);
//        System.out.println("/category/{nameCategory}");
//        if (!imgFiles.isEmpty()) {
//            return new ResponseEntity(imgFiles, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }



//
//    @RequestMapping(value="/getPhoto/{refproduct}", produces= MediaType.IMAGE_JPEG_VALUE)
//    @ResponseBody
//    public byte[] getPhotp(String refproduct) throws Exception {
//        	System.out.println(absoluteDiskPath+refproduct);
//        File f = new File(absoluteDiskPath+refproduct);
//        return IOUtils.toByteArray(new FileInputStream(f));
//    }







//    ResponseEntity<List<ImgFile>>
//    @GetMapping(path = { "/get/{imageName}" })
//    public ImgFile getImage(@PathVariable("imageName") String imageName) throws IOException {
////        List<ImgFile> img2 = new ArrayList<ImgFile>();
////         imgfileRepository.findByName2(imageName).forEach(img2::add);
////        return new ResponseEntity(img2, HttpStatus.OK);
//        final Optional<ImgFile> retrievedImage = imgfileRepository.findByName(imageName);
//        ImgFile img = new ImgFile(compressBytes(retrievedImage.get().getPicByte()), retrievedImage.get().getName(), retrievedImage.get().getType(),retrievedImage.get().getProducts()
//        );
//       return img;
//    }
    // compress the image bytes before storing it in the database
//    public static byte[] compressBytes(byte[] data) {
//        Deflater deflater = new Deflater();
//        deflater.setInput(data);
//        deflater.finish();
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//        byte[] buffer = new byte[1024];
//        while (!deflater.finished()) {
//            int count = deflater.deflate(buffer);
//            outputStream.write(buffer, 0, count);
//        }
//        try {
//            outputStream.close();
//        } catch (IOException e) {
//        }
//        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
//        return outputStream.toByteArray();
//    }
    // uncompress the image bytes before returning it to the angular application
//    public static byte[] decompressBytes(byte[] data) {
//        Inflater inflater = new Inflater();
//        inflater.setInput(data);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//        byte[] buffer = new byte[1024];
//        try {
//            while (!inflater.finished()) {
//                int count = inflater.inflate(buffer);
//                outputStream.write(buffer, 0, count);
//            }
//            outputStream.close();
//        } catch (IOException ioe) {
//        } catch (DataFormatException e) {
//        }
//        return outputStream.toByteArray();
//    }
//






//    @PostMapping("/saveImg")
//    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("imageFile") MultipartFile file) {
//        String message = "";
//        try {
//            fileStorageService.store(file);
//
//            message = "Uploaded the file successfully: " + file.getOriginalFilename();
//            System.out.println(message);
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//        } catch (Exception e) {
//            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//            System.out.println(message);
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//        }
//    }
//
//    @GetMapping("/files")
//    public ResponseEntity<List<ResponseFile>> getListFiles() {
//      List<ResponseFile> files = fileStorageService.getAllFiles()
//              .map(dbFile -> {
//                  String fileDownloadUri = ServletUriComponentsBuilder
//                          .fromCurrentContextPath()
//                          .path("/files/")
//                          .path(dbFile.getName())
//                          .toUriString();
//
//                  return new ResponseFile(
//                          dbFile.getName(),
//                          fileDownloadUri,
//                          dbFile.getType(),
//                          dbFile.getPicByte().length);
//              }).collect(Collectors.toList());
//        return ResponseEntity.status(HttpStatus.OK).body(files);
//    }
//
//    @GetMapping(path = { "/get/{imageName}" })
//    public ResponseEntity<List<ResponseFile>> getFile(@PathVariable String imageName) {
//        List<ResponseFile> files = fileStorageService.getFile(imageName).map(dbFile -> {
//            String fileDownloadUri = ServletUriComponentsBuilder
//                    .fromCurrentContextPath()
//                    .path("/files/")
//                    .path(dbFile.getName())
//                    .toUriString();
//
//            return new ResponseFile(
//                    dbFile.getName(),
//                    fileDownloadUri,
//                    dbFile.getType(),
//                    dbFile.getPicByte().length);
//        }).collect(Collectors.toList());
//        return ResponseEntity.status(HttpStatus.OK).body(files);
//    }

//      @GetMapping(path = { "/get/{imageName}" })
//    public ResponseEntity<List<ImgFile>> getListFiles(@PathVariable("imageName") String imageName) {
//        List<ImgFile> files = imgfileRepository.findByName(imageName).map(dbFile -> {
//            String fileDownloadUri = ServletUriComponentsBuilder
//                    .fromCurrentContextPath()
//                    .path("/files/")
//                    .path(dbFile.getId())
//                    .toUriString();
//
//            return new ImgFile(
//                    dbFile.getName(),
//                    fileDownloadUri,
//                    dbFile.getType(),
//                    dbFile.getData().length);
//        }).collect(Collectors.toList());
//
//        return ResponseEntity.status(HttpStatus.OK).body(files);
//    }
//








//
//    @PostMapping("/saveProduct")
//    public ResponseEntity<Products> createTutorial(@RequestBody Products product) {
//
//        try {
//            Products save = productsRepository
//                    .save(new Products(product.getRefProduct(), product.getName()
//                            ,product.getDescProduct(),product.getTotalQte(),product.getPrix(),
//                            product.isNewProduct()));
//            return new ResponseEntity<>(save, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


//    @PutMapping("/updateProduct/{refProduct}")
//    public ResponseEntity<Products> updateTutorial(@PathVariable("refProduct") String id, @RequestBody Products product) {
//        Optional<Products> tutorialData = productsRepository.findById(id);
//
//        if (tutorialData.isPresent()) {
//            Products _product = tutorialData.get();
//            _product.setName(product.getName());
//            _product.setDescProduct(product.getDescProduct());
//            _product.setTotalQte(product.getTotalQte());
//            _product.setPrix(product.getPrix());
//            return new ResponseEntity<>(productsRepository.save(_product), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/deleteProduct/{refProduct}")
//    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("refProduct") String id) {
//        try {
//            productsRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("/deleteAllProduct")
//    public ResponseEntity<HttpStatus> deleteAllTutorials() {
//        try {
//            productsRepository.deleteAll();
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
