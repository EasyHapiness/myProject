package com.ya.song.pictureserver.service.impl;

import com.ya.song.pictureserver.common.storageProperties.StorageProperties;
import com.ya.song.pictureserver.service.FileSystemStorageService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileSystemStorageServiceImpl implements FileSystemStorageService {

    private static final Logger logger = LoggerFactory.getLogger(FileSystemStorageService.class);

    private static Path location;

    @Autowired
    public FileSystemStorageServiceImpl(StorageProperties storageProperties) {
        if (storageProperties.getSystem().equals("windows")) {
            location = Paths.get(storageProperties.getRootLocation());
        } else {
            location = Paths.get(storageProperties.getServerLocation());
        }
        try {
            boolean flag = Files.exists(location);
            if (!flag) {
                Files.createDirectories(location);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String savePicture(byte[] image) {
        try {
            if (image.length <= 0) {
                throw new RuntimeException("无效的文件");
            }
            String fileName = UUID.randomUUID().toString() + ".jpg";
            //保存至外部路径
            Files.copy(new ByteArrayInputStream(image), this.location.resolve(fileName));
            Long pictureSize = pictureSize(fileName);
            if (pictureSize > 1024 * 1024) {
                deleteFile(fileName);
                return "";
            }
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to store file ");
        }
    }

    @Override
    public String savePicture(MultipartFile image) {
        try {
            if (image.isEmpty()) {
                throw new RuntimeException("无效的文件");
            }
            String fileName = UUID.randomUUID().toString() + ".jpg";
            //保存至外部路径
            Files.copy(image.getInputStream(), this.location.resolve(fileName));
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to store file " + image.getOriginalFilename());
        }
    }

    /**
     * 删除文件
     *
     * @param fileName
     * @return
     */
    @Override
    public boolean deleteFile(String fileName) {
        if (StringUtils.isBlank(fileName)) throw new RuntimeException("要删除的文件名称不能为空");
        boolean flag = false;
        try {
            File file  = new File(location.toString() + "/" + fileName);
            if (file.exists()) {
                flag = file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public Long pictureSize(String pictureName) {
        File file = new File(location.toString() + "\\" + pictureName);
        return file.length();
    }
}
