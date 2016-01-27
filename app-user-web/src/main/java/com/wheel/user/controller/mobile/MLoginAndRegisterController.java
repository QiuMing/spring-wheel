package com.wheel.user.controller.mobile;

import com.alibaba.fastjson.JSON;
import com.wheel.mybatis.dao.UserMapper;
import com.wheel.mybatis.model.User;
import com.wheel.user.model.UserForm;
import com.wheel.user.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ming on 2016/1/24.
 */
@RestController
@RequestMapping("/wheel/user")
public class MLoginAndRegisterController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/testShowUser/{id}")
    public User getUserById(@PathVariable String id){
        Integer userId = Integer.valueOf(id);
        System.out.println(userId   );
        User user = userMapper.selectByPrimaryKey(userId);
        System.out.println(JSON.toJSONString(user));
        return  user;
    }

}
