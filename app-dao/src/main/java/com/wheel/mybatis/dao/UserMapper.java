package com.wheel.mybatis.dao;

import com.wheel.mybatis.model.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    User getUserByAccount(String account);
}