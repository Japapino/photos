package com.murakami.example.photosclone.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.murakami.example.photosclone.model.Photo;
import com.murakami.example.photosclone.service.PhotosService;

@RestController
public class DownloadController {

    private final PhotosService photosService;

    public DownloadController(@Autowired PhotosService PhotosService) {
        this.photosService = PhotosService;
    };

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(@PathVariable("id") Integer id) {
        Photo photo = photosService.get(id);
        if (photo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        byte[] data = photo.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photo.getContentType()));

        ContentDisposition build = ContentDisposition.builder("attachment")
            .filename(photo.getFileName())
            .build();
        headers.setContentDisposition(build);
        
        return new ResponseEntity<>(data, headers, HttpStatus.OK);

    }
}
