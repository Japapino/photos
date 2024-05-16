package com.murakami.example.photosclone.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.murakami.example.photosclone.model.Photo;

@Repository
public interface PhotosRepository extends CrudRepository<Photo, Integer> {
}