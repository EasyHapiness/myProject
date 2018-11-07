
package com.ya.song.userserver.mapper;
import com.ya.song.userserver.entity.User;

public interface UserMapper {

    User selectByPrimaryKey(Long id);
}
