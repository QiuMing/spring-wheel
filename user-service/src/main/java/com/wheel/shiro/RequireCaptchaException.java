package com.wheel.shiro;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by Ming on 2015/12/17.
 */
public class RequireCaptchaException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public RequireCaptchaException() {
        super();
    }

    public RequireCaptchaException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequireCaptchaException(String message) {
        super(message);
    }

    public RequireCaptchaException(Throwable cause) {
        super(cause);
    }

}
