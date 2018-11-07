package com.ya.song.pictureserver.controller;

import com.ya.song.pictureserver.entity.Picture;
import com.ya.song.pictureserver.service.PictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Controller
@RequestMapping("/picture")
public class PictureController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    PictureService pictureService;

    @ResponseBody
    @RequestMapping(value = "/info/{userId}",method = RequestMethod.GET)
    public Object findUserById(@PathVariable("userId") Long userId){

        return getSuccessResult(pictureService.findById(userId));
    }

    @ResponseBody
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Object findUserById(MultipartFile image, Picture picture){

        pictureService.saveImage(image,picture);

        return getSuccessResult("上传成功");
    }
}
