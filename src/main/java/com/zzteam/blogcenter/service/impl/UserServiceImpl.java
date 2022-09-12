package com.zzteam.blogcenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzteam.blogcenter.model.domain.request.UserLoginRequest;
import com.zzteam.blogcenter.model.domain.request.UserRegisterRequest;
import com.zzteam.blogcenter.utils.R;
import com.zzteam.blogcenter.model.domain.User;
import com.zzteam.blogcenter.service.UserService;
import com.zzteam.blogcenter.Mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zzteam.blogcenter.Contant.userContant.USER_LOGIN_STATE;

/**
 * 用户服务实现类
 *
 * @author zj
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;

    private static final String SALT = "zj";

    @Override
    public R<User> UserRegister(UserRegisterRequest usrRegister) {
        String userAccount = usrRegister.getUserAccount();
        String userPassword = usrRegister.getUserPassword();
        User user = new User();
        //简单验证即可
        //1.验证不为空
        if (StringUtils.isAnyBlank(userAccount, userPassword)){
            return R.error("数据为空");
        }
        //2.验证密码位数大于6小于12
        if (userPassword.length() < 6 || userPassword.length() > 12){
            return R.error("密码位数错误");
        }
        // 3.账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()){
            return R.error("账户不能包含特殊字符");
        }
        //4.账户不能重复
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount",userAccount);
        long count = userMapper.selectCount(userQueryWrapper);
        if (count > 0){
            return R.error("账户不能重复");
        }

        //加密
        String s = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        user.setUserAccount(userAccount);
        user.setUserPassword(s);
        boolean save = this.save(user);
        if (!save){
            return R.error("用户注册失败，请重试！");
        }
        User safetyUser = getSafetyUser(user);
        return R.success(safetyUser);
    }

    @Override
    public R<User> userLogin(UserLoginRequest userLogin, HttpServletRequest request) {
        String userAccount = userLogin.getUserAccount();
        String userPassword = userLogin.getUserPassword();
        //1.不为空
        if (StringUtils.isAnyBlank(userAccount, userPassword)){
            return R.error("账号、密码不能为空");
        }
        //2.验证密码位数大于6小于12
        if (userPassword.length() < 6 || userPassword.length() > 12){
            return R.error("密码位数错误");
        }
        //3.验证账户存在
        //加密
        String sed = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", userAccount);
        userQueryWrapper.eq("userPassword", sed);
        User user = userMapper.selectOne(userQueryWrapper);
        if (user == null){
            return R.error("用户登录失败，账号密码不匹配");
        }
        //获取脱敏后的用户
        User safetyUser = getSafetyUser(user);
        //记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE,safetyUser);
        request.getSession().setAttribute("loginUser", safetyUser);
        return R.success(safetyUser);
    }

    /**
     * 获取脱敏后的用户数据
     * @param user
     * @return
     */
    @Override
    public User getSafetyUser(User user){
        //返回脱敏后的用户信息
        User safetyuser = new User();
        safetyuser.setId(user.getId());
        safetyuser.setUsername(user.getUsername());
        safetyuser.setUserAccount(user.getUserAccount());
        safetyuser.setAvatarUrl(user.getAvatarUrl());
        safetyuser.setGender(user.getGender());
        safetyuser.setEmail(user.getEmail());
        safetyuser.setUserStatus(user.getUserStatus());
        safetyuser.setPhone(user.getPhone());
        safetyuser.setCreateTime(user.getCreateTime());
        safetyuser.setUpdateTime(user.getUpdateTime());
        safetyuser.setIsDelete(user.getIsDelete());
        safetyuser.setUserRole(user.getUserRole());
        return safetyuser;
    }
}




