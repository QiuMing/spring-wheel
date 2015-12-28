package com.wheel.user.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.wheel.mybatis.model.User;
import com.wheel.user.model.UserForm;
import com.wheel.user.service.UserServiceI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * Created by Ming on 2015/12/11.
 */
@Controller
@RequestMapping("/wheel/user")
public class LoginAndRegisterController {
    @Autowired
    private UserServiceI userServiceI;
    @Autowired
    private Producer captchaProducer;

    private final Log logger = LogFactory.getLog(getClass());

    @RequestMapping("register")
    public String register(ModelMap model){
        model.put("user",new UserForm());
        return "register";
    }

    @RequestMapping(value="login")
    public String login(ModelMap model){
        model.put("user",new UserForm());
        return "login";
    }

    @RequestMapping("captcha")
    public void showCaptcha(HttpServletResponse response, HttpServletRequest request) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText();
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    //------------------------------------------------
    @RequestMapping("/helloJson")
    @ResponseBody
    public  String helloJson() {
        logger.info("传输json");
        return "返回json 格式的字符串";
    }

    @RequestMapping("/helloVelocity")
    public String home(ModelMap model) {
        logger.info("This is home");
        model.put("time", new Date());
        return "helloVelocity";
    }

    @RequestMapping("test")
    public @ResponseBody User tests(){
        User user = new User("明","名字","aaa");
        logger.info("aaaaa");
        //int a  = Integer.valueOf("aaaaa");
        return user;
    }
}
