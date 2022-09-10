package com.zzteam.blogcenter.service.impl;

import com.zzteam.blogcenter.model.domain.User;
import com.zzteam.blogcenter.service.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

/**
 * 用户服务测试
 *
 * @author zj
 *
 */

@SpringBootTest
class UserServiceImplTest {

    @Resource
    private UserService userService;

    @Test
    public void testAddUser(){
        User user = new User();
        user.setUsername("xxx");
        user.setUserAccount("xxx");
        user.setAvatarUrl("https://thinkingcao.blog.csdn.net/");
        user.setGender((byte)0);
        user.setUserPassword("111111");
        user.setEmail("wsi163@.com");
        user.setPhone("1221212121");
        boolean save = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(save);
    }

}