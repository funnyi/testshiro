package com.study.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.boot.domain.User;
import com.study.boot.mapper.UserMapper;
import com.study.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Autowired
    UserMapper userMapper;
    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
