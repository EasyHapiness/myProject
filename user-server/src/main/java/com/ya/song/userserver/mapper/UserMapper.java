
package com.ya.song.userserver.mapper;

import com.ya.song.userserver.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    User selectByPrimaryKey(Long id);

    @Update("UPDATE `user` SET `name` =  #{name},modify_time = NOW() WHERE `id` = #{id}")
    int updateNameById(@Param("name") String name,@Param("id") long id);

    @Update("UPDATE `user` SET `sex` =  #{sex},modify_time = NOW() WHERE `id` = #{id}")
    int updateSexById(@Param("sex") String sex,@Param("id") long id);
}
