package com.study.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.boot.domain.User;

public interface UserService extends IService<User> {
    User getUserByUsername(String username);
}
