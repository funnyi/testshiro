package com.study.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.boot.domain.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper extends BaseMapper<User> {
    User getUserByUsername(String username);
}
