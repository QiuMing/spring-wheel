package com.wheel.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Ming on 2015/12/20.
 */
public class AdminController {

    @RequestMapping("ad")
    public String adminPage(){
        return "admin";
    }
}
