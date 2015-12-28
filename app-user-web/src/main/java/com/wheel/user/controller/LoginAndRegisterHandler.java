package com.wheel.user.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.wheel.mybatis.model.User;
import com.wheel.shiro.RequireCaptchaException;
import com.wheel.shiro.UsernamePasswordCaptchaToken;
import com.wheel.shiro.WrongCaptchaException;
import com.wheel.user.model.UserForm;
import com.wheel.user.service.UserServiceI;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by Ming on 2015/12/11.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/wheel/user")
public class LoginAndRegisterHandler {

    @Autowired
    private UserServiceI userServiceI;

    private static final Logger logger = Logger.getLogger(LoginAndRegisterHandler.class);

    @RequestMapping(value="doLogin", method = RequestMethod.POST)
    public String doLogin(@Valid @ModelAttribute("user") UserForm user, BindingResult bindingResult,ModelMap model){
        logger.info(JSON.toJSONString(user));


        //注解级别的验证过滤
        if (bindingResult.hasErrors()) {
            logger.info(bindingResult.getAllErrors());
            //return "login";
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordCaptchaToken token = new UsernamePasswordCaptchaToken(user.getAccount(),user.getPassword().toCharArray(),true,user.getCaptcha());
        String error = null;

        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            error = "账号不存在";
            bindingResult.rejectValue("account", "account_not_exist");
           //bindingResult.rejectValue("accoun","account_not_exist","账号不存在");

        } catch (RequireCaptchaException e) {
            error = "密码错误次数过多，显示验证码";
            bindingResult.rejectValue("password", "wrong_password");
            model.put("needCaptcha","needCaptcha");

        } catch (IncorrectCredentialsException e) {
            error = "用户名/密码错误";
            bindingResult.rejectValue("password", "wrong_password");

        } catch (WrongCaptchaException e){
            error = "验证码错误";
            model.put("needCaptcha","needCaptcha");
            bindingResult.rejectValue("captcha", "wrong_captcha");

        }catch (ExcessiveAttemptsException e) {
            error = "登录失败多次，账户锁定10分钟";
            bindingResult.rejectValue("account", "account_is_locked_10_minute");

        } catch (AuthenticationException e) {
            error = "其他错误：" + e.getMessage();
            bindingResult.rejectValue("account", "unknow_error");
        }

        if (error != null) {
            logger.info(error);
            return "login";
        } else {
            return "success";
        }
    }

    @RequestMapping(value="doRegister", method = RequestMethod.POST)
    public String doRegister(@Valid @ModelAttribute("user") UserForm user, BindingResult bindingResult){
        logger.info(JSON.toJSONString(user));
        if(StringUtils.isEmpty(user.getAccount())||StringUtils.isEmpty(user.getPassword())||StringUtils.isEmpty(user.getNickName())){
            return "error";
        }

        if(userServiceI.getUserByAccount(user.getAccount())==null){
            bindingResult.rejectValue("account", "Account has exist");
            return "register";
        }

        String result = userServiceI.register(user);
        if(result.equals("success"))
            return "success";
        else
            return "fail";
    }


    @RequestMapping(value="ajaxLogin", method = RequestMethod.POST)
    public @ResponseBody String ajaxLogin(){
        return "success";
    }

    public boolean  isExistUser(String account){
        return userServiceI.getUserByAccount(account)!=null? true:false;
    }

    @RequestMapping("/logout")
    public void doLogout(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        String url = request.getHeader("Referer");
        logger.info("You are sign out and current url is:" + url);
        if(StringUtils.isEmpty(url))
            response.sendRedirect("/wheel/user/login");
        else
            response.sendRedirect(url);
    }
}
