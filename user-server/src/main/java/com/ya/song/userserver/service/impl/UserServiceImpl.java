package com.ya.song.userserver.service.impl;

import com.ya.song.userserver.entity.User;
import com.ya.song.userserver.mapper.UserMapper;
import com.ya.song.userserver.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    UserMapper userMapper;

    @Override
    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateNameById(String name, long id) {
        userMapper.updateNameById(name,id);

        upSexById("1",id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateSexById(String name, long id) {
        userMapper.updateSexById(name,id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void testTranscation(String name, long id,String sex) {

        /**
         * 经测试结果如下：如果controller入口service加有注解则不管service内部方法有没有@Transactional都起作用
         * 不管内部方法是public还是private(如果内部方法还有调用内部方法事物也会起作用);
         *
         * 如果controller入口service没有加@Transactional则不管service内部方法有没有@Transactional都不起作用
         *
         */
        logger.info("++++ " + name);
        updateNameById(name,id);

        /*if (StringUtils.isNotBlank(sex)){
            updateSexById(sex,id);
        }*/

        User user = findById(id);
        logger.info("++++ " + name);
        String string = null;
        if (1==1){
            throw new RuntimeException();
        }
        logger.info("user "+ user.toString());
    }


    public void upNameById(String name, long id) {
        userMapper.updateNameById(name,id);
    }

    private void upSexById(String sex, long id) {
        userMapper.updateSexById(sex,id);
    }

}
