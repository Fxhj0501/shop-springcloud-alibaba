package io.shop.user.service;

import io.shop.bean.User;

public interface UserService {

    /**
     * 根据id获取用户信息
     */
    User getUserById(Long userId);
}