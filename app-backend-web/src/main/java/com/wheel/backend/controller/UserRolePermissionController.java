package com.wheel.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by Ming on 2015/11/4.
 */
@Controller
public class UserRolePermissionController {

    @RequestMapping("/helloJson")
    @ResponseBody
    public  String helloJson() {
        System.out.print("传输json");
        return "welcome";
    }

    @RequestMapping("/helloVelocity")
    public String home(ModelMap model) {
        //System.out.print("This is home");
        model.put("time", new Date());
        return "helloVelocity";
    }
}
