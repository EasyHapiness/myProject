package com.ya.song.pictureserver.service.impl;

import com.ya.song.pictureserver.common.storageProperties.StorageProperties;
import com.ya.song.pictureserver.entity.Picture;
import com.ya.song.pictureserver.mapper.PictureMapper;
import com.ya.song.pictureserver.service.FileSystemStorageService;
import com.ya.song.pictureserver.service.PictureService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    @Resource
    PictureMapper pictureMapper;

    @Resource
    StorageProperties storageProperties;

    @Resource
    FileSystemStorageService fileSystemStorageService;

    @Override
    public List<Picture> findById(Long userId) {
        return pictureMapper.selectByUserId(userId);
    }

    @Override
    public void saveImage(MultipartFile image,Picture picture) {

        String imageName = null;
        try {
            if (null != image){
                imageName = fileSystemStorageService.savePicture(image);
                imageName = storageProperties.getRequestLocation() + imageName;
                picture.setPictureName(image.getName()+".jpg");
                picture.setPictureUrl(imageName);
            }
            pictureMapper.insert(picture);
        }catch (Exception e){
            fileSystemStorageService.deleteFile(imageName);
        }

    }

}
