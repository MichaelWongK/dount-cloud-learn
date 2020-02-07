package com.dount.cloud.dountuserservice.service;

import com.dount.cloud.dountuserservice.domain.User;

import java.util.List;

/**
 * @author micheal.wang <a href="michael.won007@gmail.com"/>
 * @create 2020-02-06
 */
public interface UserService {

    void create(User user);

    User getUser(Long id);

    void update(User user);

    void delete(Long id);

    User getByUserName(String username);

    List<User> getByUserIds(List<Long> ids);
}
