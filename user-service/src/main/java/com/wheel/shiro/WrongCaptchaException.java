package com.wheel.shiro;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by Ming on 2015/12/17.
 */
public class WrongCaptchaException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public WrongCaptchaException() {
        super();
    }

    public WrongCaptchaException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongCaptchaException(String message) {
        super(message);
    }

    public WrongCaptchaException(Throwable cause) {
        super(cause);
    }

}