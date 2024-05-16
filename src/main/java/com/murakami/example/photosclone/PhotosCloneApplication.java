package com.murakami.example.photosclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class PhotosCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotosCloneApplication.class, args);
	}

}
