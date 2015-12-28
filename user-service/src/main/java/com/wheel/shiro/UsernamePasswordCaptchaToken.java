package com.wheel.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by Ming on 2015/12/17.
 */
public class UsernamePasswordCaptchaToken  extends UsernamePasswordToken {
    private static final long serialVersionUID = 1L;
    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public UsernamePasswordCaptchaToken() {
        super();
    }

    public UsernamePasswordCaptchaToken(String username, char[] password, boolean rememberMe,  String captcha){
        super(username, password, rememberMe);
        this.captcha = captcha;
    }

    public UsernamePasswordCaptchaToken(String username, char[] password, boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }

}