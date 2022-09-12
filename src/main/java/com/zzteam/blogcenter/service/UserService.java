package com.zzteam.blogcenter.service;

import com.zzteam.blogcenter.model.domain.request.UserLoginRequest;
import com.zzteam.blogcenter.model.domain.request.UserRegisterRequest;
import com.zzteam.blogcenter.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzteam.blogcenter.model.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
 *用户服务接口
 */
public interface UserService extends IService<User> {
    /**
     * 用户注册
     * @param usrRegister
     * @return
     */
    R<User> UserRegister(UserRegisterRequest usrRegister);

    /**
     * 用户登录方法
     * @param userLogin
     * @param request
     * @return
     */
    R<User> userLogin(UserLoginRequest userLogin, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param user
     * @return
     */
    User getSafetyUser(User user);
}
