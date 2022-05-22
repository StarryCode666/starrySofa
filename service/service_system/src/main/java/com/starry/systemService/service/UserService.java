package com.starry.systemService.service;

import com.starry.systemService.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author starry
 * @since 2022-05-22
 */
public interface UserService extends IService<User> {
    void register(User user);

    String login(User userLoginVo);
}
