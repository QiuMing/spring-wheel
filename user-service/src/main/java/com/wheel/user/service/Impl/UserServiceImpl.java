package com.wheel.user.service.Impl;

import SecurityTool.RandomNumUtils;
import com.alibaba.fastjson.JSON;
import com.wheel.mybatis.dao.UserMapper;
import com.wheel.mybatis.model.User;
import com.wheel.user.service.UserServiceI;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Ming on 2015/11/30.
 */
@Service
public class    UserServiceImpl implements UserServiceI {

    @Autowired
    public UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger("UserServiceImpl.class");

    public User getUserByAccount(String account){
        return userMapper.getUserByAccount(account);
    }

    public String register(User user){
        if(StringUtils.isEmpty(user.getAccount())||StringUtils.isEmpty(user.getPassword())||StringUtils.isEmpty(user.getNickName())){
            logger.info("");
        }
        //user.setSalt(RandomStringUtils.randomNumeric(5));
        //user.setSalt(new SecureRandomNumberGenerator().nextBytes().toHex();
        DefaultPasswordService a = new DefaultPasswordService();
        String  salt = RandomNumUtils.uuid16();
        user.setSalt(salt);
        user.setIsActive(true);
        String password =  new SimpleHash("SHA-256",user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()),2).toHex();
        user.setPassword(password);
        if(userMapper.insertSelective(user)<1){
            return "addUserFail";
        }

        return "success";


        /*
        如果是邮箱注册，此处还应该发送邮件,active 应该在邮箱激活后为true
         */
    }

    public String  getActiveEmailUrl(String salt,String email){
        String encrptContent = new  SimpleHash("SHA-256",email,ByteSource.Util.bytes(salt),3).toHex();
        return "ecyt="+encrptContent;
    }

    public boolean isLegalActiveEmailUrl(String encryptString,String salt,String email){
        if(StringUtils.isEmpty(encryptString))
            return false;
        return encryptString.equals(new SimpleHash("SHA-256",email,ByteSource.Util.bytes(salt),3).toHex());
    }

}
