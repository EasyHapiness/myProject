package com.ya.song.userserver.controller;

import com.ya.song.userserver.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    public Object findUserById(@PathVariable("id") Long id){

        return getSuccessResult(userService.findById(id));
    }

    @ResponseBody
    @RequestMapping(value = "/update/{id}/{name}/{sex}",method = RequestMethod.GET)
    public Object updateNameById(@PathVariable("id") Long id,@PathVariable("name") String name,@PathVariable("sex") String sex){

        userService.testTranscation(name,id,sex);
        return getSuccessResult("测试成功！");
    }
}
