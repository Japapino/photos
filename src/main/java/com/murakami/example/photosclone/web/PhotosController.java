package com.murakami.example.photosclone.web;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.murakami.example.photosclone.model.Photo;
import com.murakami.example.photosclone.service.PhotosService;

@RestController
public class PhotosController {

    private final PhotosService photosService;

    public PhotosController(@Autowired PhotosService photosService) {
        this.photosService = photosService;
    };
    
    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/photos")
    public Iterable<Photo> get() {
        return photosService.getAll();
    }

    @GetMapping("/photos/{id}")
    public Photo get(@PathVariable("id") Integer id) {
        Photo photo = photosService.get(id);

        if (photo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return photo;
    }

    @DeleteMapping("/photos/{id}") 
    public void delete(@PathVariable("id") Integer id) {
        Photo photo = photosService.get(id);
        if (photo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/photos") //note "data" is the name of the form field and matches html form field name
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        return photosService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }


}
