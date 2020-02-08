package com.dount.cloud.feignservice.service.Impl;

import com.dount.cloud.feignservice.domain.CommonResult;
import com.dount.cloud.feignservice.domain.User;
import com.dount.cloud.feignservice.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @author micheal.wang <a href="michael.won007@gmail.com"/>
 * @create 2020-02-08
 */
@Component
public class UserFallbackService implements UserService {
    @Override
    public CommonResult create(User user) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(defaultUser);
    }

    @Override
    public CommonResult<User> getUser(Long id) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(defaultUser);
    }

    @Override
    public CommonResult<User> getByUsername(String username) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(defaultUser);
    }

    @Override
    public CommonResult update(User user) {
        return new CommonResult("调用失败，服务被降级",500);
    }

    @Override
    public CommonResult delete(Long id) {
        return new CommonResult("调用失败，服务被降级",500);
    }
}
