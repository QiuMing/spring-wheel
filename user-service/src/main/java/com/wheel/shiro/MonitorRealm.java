package com.wheel.shiro;

import com.alibaba.fastjson.JSON;
import com.wheel.mybatis.model.User;
import com.wheel.user.service.UserServiceI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.util.ByteSource;
/**
 * Created by Ming on 2015/12/13.
 */
public class MonitorRealm extends AuthorizingRealm {

    @Autowired
    private UserServiceI userService;

    private final Log logger = LogFactory.getLog(getClass());
    private final static String REALM_NAME = "MonitorRealm";

    public MonitorRealm() {
        setName(REALM_NAME);
    }

    /*
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String account = (String) super.getAvailablePrincipal(principals);
        return null;
    }

    /*
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
    {
        System.out.println("获取token"+JSON.toJSONString(authcToken));
        UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken;

        User user = userService.getUserByAccount((token.getUsername()));
        if (user == null) {
            throw new UnknownAccountException();
        }

        if(!user.getIsActive()){
            throw new DisabledAccountException();
        }

       SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
               user.getAccount(), user.getPassword(),
               ByteSource.Util.bytes(user.getCredentialsSalt()),// salt=account+salt
               getName() // realm name
        );
        return authenticationInfo;
    }
}
