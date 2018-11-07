package com.ya.song.userserver.service;

import com.ya.song.userserver.entity.User;

public interface UserService {

    User findById(Long id);
}
