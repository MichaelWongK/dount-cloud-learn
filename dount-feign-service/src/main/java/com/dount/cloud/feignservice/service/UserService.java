package com.dount.cloud.feignservice.service;

import com.dount.cloud.feignservice.domain.CommonResult;
import com.dount.cloud.feignservice.domain.User;
import com.dount.cloud.feignservice.service.Impl.UserFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Desprition 设置服务降级处理类为UserFallbackService
 * 修改@FeignClient注解中的参数，设置fallback为UserFallbackService.class即可。
 *
 * @author micheal.wang <a href="michael.won007@gmail.com"/>
 * @create 2020-02-08
 */
@FeignClient(value = "user-service", fallback = UserFallbackService.class)
public interface UserService {

    @PostMapping("/user/create")
    CommonResult create(@RequestBody User user);

    @GetMapping("/user/{id}")
    CommonResult<User> getUser(@PathVariable(value = "id") Long id);

    @GetMapping("/user/getByUsername")
    CommonResult<User> getByUsername(@RequestParam("username") String username);

    @PostMapping("/user/update")
    CommonResult update(@RequestBody User user);

    @PostMapping("/user/delete/{id}")
    CommonResult delete(@PathVariable(value = "id") Long id);

}
