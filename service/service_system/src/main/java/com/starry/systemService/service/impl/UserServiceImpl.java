package com.starry.systemService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.starry.commonService.handler.StarryException;
import com.starry.commonUtils.JwtUtils;
import com.starry.commonUtils.MD5;
import com.starry.systemService.entity.User;
import com.starry.systemService.mapper.UserMapper;
import com.starry.systemService.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author starry
 * @since 2022-05-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    //用户注册
    @Override
    public void register(User user) {
        //校验非空字段
        if (StringUtils.isEmpty(user.getUserId()) || StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getPhone())){
            throw new StarryException(20001,"必填字段为空！");
        }
        //校验账号和手机号是否重复
        QueryWrapper<User> userIdQueryWrapper = new QueryWrapper<>();
        userIdQueryWrapper.eq("user_id",user.getUserId());
        int countId = baseMapper.selectCount(userIdQueryWrapper);
        QueryWrapper<User> userPhoneQueryWrapper = new QueryWrapper<>();
        userPhoneQueryWrapper.eq("phone",user.getPhone());
        int countPhone = baseMapper.selectCount(userPhoneQueryWrapper);
        if (countId>0 || countPhone>0){
            throw new StarryException(20001,"账号或者手机号重复");
        }
        //加密密码
        user.setPassword(MD5.encrypt(user.getPassword()));
        //插入数据库
        baseMapper.insert(user);
    }

    //用户登录
    @Override
    public String login(User userLoginVo) {
        //校验非空字段
        if (StringUtils.isEmpty(userLoginVo.getUserId()) || StringUtils.isEmpty(userLoginVo.getPassword())){
            throw new StarryException(20001,"必填字段为空！");
        }
        //查询用户账号
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", userLoginVo.getUserId());
        User user = baseMapper.selectOne(userQueryWrapper);
        if (null == user || !user.getPassword().equals(MD5.encrypt(userLoginVo.getPassword()))){
            throw new StarryException(20001,"用户名或密码错误");
        }
        //获取token
        String token = JwtUtils.getJwtToken(user.getUserId(),user.getUserName());
        return token;
    }
}
