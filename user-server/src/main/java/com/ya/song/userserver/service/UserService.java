package com.ya.song.userserver.service;

import com.ya.song.userserver.entity.User;

public interface UserService {

    User findById(Long id);

    void updateNameById(String name,long id);

    void updateSexById(String name,long id);

    void testTranscation(String name,long id,String sex);
}
