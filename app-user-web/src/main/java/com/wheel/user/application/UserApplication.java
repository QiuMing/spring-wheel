package com.wheel.user.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Ming on 2015/11/4.
 */

@SpringBootApplication
public class UserApplication {
   public static void main(String[] args) {
       SpringApplication.run(new String[]{
               "classpath*:/config/spring-*.xml",
             /*  "classpath*:/config/spring-mybatis.xml",
               "classpath*:/config/spring-i18n.xml",
               "classpath*:/config/spring-shiro.xml",*/
               "classpath*:/config/captcha.xml"
       }, args);
   }
}