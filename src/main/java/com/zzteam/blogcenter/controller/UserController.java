package com.zzteam.blogcenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzteam.blogcenter.utils.R;
import com.zzteam.blogcenter.model.domain.User;
import com.zzteam.blogcenter.model.domain.request.UserLoginRequest;
import com.zzteam.blogcenter.model.domain.request.UserRegisterRequest;
import com.zzteam.blogcenter.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;

import static com.zzteam.blogcenter.Contant.userContant.ADMIN_ROLE;
import static com.zzteam.blogcenter.Contant.userContant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/register")
    @ApiOperation("注册")
    public R userRegister(@ModelAttribute UserRegisterRequest usrRegister){
        if (usrRegister == null){
            return R.error("数据为空");
        }
        String userAccount = usrRegister.getUserAccount();
        String userPassword = usrRegister.getUserPassword();
        return userService.UserRegister(userAccount, userPassword);
    }

    @PostMapping("/login")
    @ApiOperation("登录")
    public R userLogin(@ModelAttribute UserLoginRequest userLogin, HttpServletRequest request){
        if (userLogin == null){
            return R.error("数据为空");
        }
//        String userAccount = userLogin.getUserAccount();
//        String userPassword = userLogin.getUserPassword();
        //不用心急取出
        return userService.userLogin(userLogin, request);
    }

    @PostMapping("/search")
    @ApiOperation("管理员查询用户")
    public R searchUsers(String username, HttpServletRequest request){
        //仅管理员可查
        if (!isAdmin(request)){
            return R.error("您没有权限进行查询用户操作，谢谢！");
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)){
            userQueryWrapper.like("username", username);
        }
        List<User> userslist = userService.list(userQueryWrapper);
        return R.success(userslist.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList()));
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation("管理员删除用户")
    public R userDel(@PathVariable long id, HttpServletRequest request){
        //仅管理员可删
        if (!isAdmin(request)){//如果不是管理员
            return R.error("您没有权限进行用户删除操作，谢谢！");
        }
        if (id <= 0){
            return R.error("该id不合法，请重新输入");
        }
        if (userService.removeById(id)){
            return R.success("用户删除成功");
        }
        return R.success("用户删除失败");
    }

    /**
     * 是否为管理员
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User)userObj;
        //0为普通用户，ADMIN_ROLE为管理员
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }



}
