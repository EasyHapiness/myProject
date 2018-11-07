
package com.ya.song.pictureserver.mapper;

import com.ya.song.pictureserver.entity.Picture;

import java.util.List;

public interface PictureMapper {

    List<Picture> selectByUserId(Long userId);

    void insert(Picture picture);
}
