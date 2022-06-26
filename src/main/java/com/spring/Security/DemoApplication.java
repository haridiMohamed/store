package com.spring.Security;

import com.spring.Security.dao.ProductsRepository;
import com.spring.Security.entity.Products;
import com.spring.Security.webSecurity.serves.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Resource
	FileStorageService fileStorageService;
    public static void main(String[] args) {


		SpringApplication.run(DemoApplication.class, args);

	}
	@Override
	public void run(String... args) throws Exception {

//		fileStorageService.deleteAll();
//		fileStorageService.init();
		String relativeWebPath ="ecommerce"+File.separator;
		String absoluteDiskPath =  System.getProperty("user.home")+File.separator+relativeWebPath;
		String relativeWebPath2 ="clients"+File.separator;
		String relativeWebPath1 ="products"+File.separator;
		// add file
		File file2 = new File(absoluteDiskPath,relativeWebPath2);
		File file1 = new File(absoluteDiskPath,relativeWebPath1);
		file2.mkdirs();
		file1.mkdirs();
		if (!file2.exists()) {
			if (file2.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}
	}
}
