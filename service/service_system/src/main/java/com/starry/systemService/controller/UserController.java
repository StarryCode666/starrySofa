package com.starry.systemService.controller;


import com.starry.commonUtils.JwtUtils;
import com.starry.commonUtils.R;
import com.starry.systemService.entity.User;
import com.starry.systemService.service.UserService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author starry
 * @since 2022-05-22
 */
@RestController
@RequestMapping("/systemService/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userList")
    public R getUserList(){
        Map<String,Object> res = new HashMap<>();
        List<User> userList = userService.list();
        res.put("data",userList);
        return R.ok().data(res);
    }

    @ApiOperation("注册用户")
    @PostMapping("addUser")
    public R addUser(@RequestBody User user){
        userService.register(user);
        return R.ok().data("token", JwtUtils.getJwtToken(user.getUserId(),user.getUserName()));
    }
}

