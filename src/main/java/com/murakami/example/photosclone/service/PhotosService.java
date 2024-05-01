package com.murakami.example.photosclone.service;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.murakami.example.photosclone.model.Photo;

// @Component <- Both @Component and @Service are valid annotations for this class
@Service
public class PhotosService {


    private Map<String, Photo> db = new HashMap<>() {{
        put("1", new Photo("1", "hello.jpg"));
    }};

    public Collection<Photo> getAll() {
        return db.values();
    }
    
    public Photo get(String id) {
        return db.get(id);
    }


	public void remove(String id) {
        db.remove(id);
	}

	public Photo save(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setId(UUID.randomUUID().toString());
        photo.setFileName(fileName);
        photo.setData(data);
        photo.setContentType(contentType);
        
        db.put(photo.getId(), photo);

        return photo;
	}

}