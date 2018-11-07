package com.ya.song.userserver.service.impl;

import com.ya.song.userserver.entity.User;
import com.ya.song.userserver.mapper.UserMapper;
import com.ya.song.userserver.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
