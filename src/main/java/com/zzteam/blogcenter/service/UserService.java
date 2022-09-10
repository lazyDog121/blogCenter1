package com.zzteam.blogcenter.service;

import com.zzteam.blogcenter.model.domain.request.UserLoginRequest;
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
     * @param userAccount
     * @param passWord
     * @return
     */
    R UserRegister(String userAccount, String passWord);

    /**
     * 用户登录方法
     * @param userAccount 用户账户
     * @param userPassWord 用户密码
     * @param request
     * @return
     */
    R userLogin(UserLoginRequest userLogin, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param user
     * @return
     */
    User getSafetyUser(User user);
}
