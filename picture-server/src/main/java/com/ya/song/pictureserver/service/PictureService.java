package com.ya.song.pictureserver.service;

import com.ya.song.pictureserver.entity.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PictureService {

    List<Picture> findById(Long userId);

    void saveImage(MultipartFile image,Picture picture);
}
