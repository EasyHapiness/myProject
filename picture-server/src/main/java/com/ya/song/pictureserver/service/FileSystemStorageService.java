package com.ya.song.pictureserver.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileSystemStorageService {

    String savePicture(byte[] image);

    String savePicture(MultipartFile image);

    boolean deleteFile(String fileName);
}
