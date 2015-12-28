package com.wheel.shiro;

import com.alibaba.fastjson.JSON;
import com.wheel.config.ConfigConstant;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.LogFactory;
import org.apache.juli.logging.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import com.google.code.kaptcha.Constants;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by Ming on 2015/12/13.
 */
public class RetryLimitedCredentials extends HashedCredentialsMatcher {

    private final org.apache.commons.logging.Log logger = LogFactory.getLog(getClass());

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        System.out.println("这里开始验证身份");
        UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken;
        System.out.println("--" + JSON.toJSONString(token));

        String account = (String) token.getPrincipal();
        AtomicInteger retryCount = passwordRetryCache.get(account);
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(account, retryCount);
        }
        logger.info(account+ "正在尝试登陆" + retryCount + "次");

        if (retryCount.incrementAndGet() > ConfigConstant.WRONG_PASSWORD_TIMES_TO_LOCK_ACCOUNT) {
            throw new ExcessiveAttemptsException();
        }

        String inputCaptcha  = token.getCaptcha();
        if((retryCount.intValue() > ConfigConstant.WRONG_PASSWORD_TIMES_TO_SHOW_CAPTCHA)
                ||StringUtils.isNotEmpty(inputCaptcha)){

            String captchaInSession =(String) SecurityUtils.getSubject().getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if(StringUtils.isEmpty(inputCaptcha) || !inputCaptcha.equalsIgnoreCase(captchaInSession)){
                SecurityUtils.getSubject().getSession().removeAttribute(Constants.KAPTCHA_SESSION_CONFIG_KEY);
                throw new WrongCaptchaException("Error Captcha");
            }
            SecurityUtils.getSubject().getSession().removeAttribute(Constants.KAPTCHA_SESSION_CONFIG_KEY);
        }

        boolean matches = super.doCredentialsMatch(authcToken, info);
        if (matches) {
            passwordRetryCache.remove(account);
        }else{
            if(retryCount.intValue() >= ConfigConstant.WRONG_PASSWORD_TIMES_TO_SHOW_CAPTCHA){
                throw  new RequireCaptchaException("Wrong Password,Show Captcha");
            }
        }
        return matches;
    }

    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitedCredentials(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }
}
