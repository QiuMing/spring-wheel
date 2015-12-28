package com.wheel.user.service;

import com.wheel.mybatis.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by Ming on 2015/11/30.
 */
public interface UserServiceI {
    String register(User user);
    User getUserByAccount(String account);
}
