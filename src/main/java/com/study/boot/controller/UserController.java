package com.study.boot.controller;


import com.study.boot.domain.User;
import com.study.boot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/myController")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/")
    public String index(){
        return "login";
    }


    @PostMapping("/userLogin")
    public String user(String name,
                       String pwd,
                       @RequestParam(value = "rememberMe",defaultValue = "false") boolean rememberMe,
                       HttpSession session
    ){
        //获取subject对象
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(name,pwd,rememberMe);
        try{
            subject.login(token);
            session.setAttribute("user",name);
            return "main";
        }catch (AuthenticationException e){
            return "error";
        }
    }





    @GetMapping("userLoginRm")
    public String userLogin(HttpSession session){
        session.setAttribute("user","rememberMe");
        return "main";
    }



    @RequiresRoles({"admin"})
    @GetMapping("requiresRoles")
    @ResponseBody
    public String requiresRoles(){
        return "有次角色";
    }


    @RequiresPermissions({"user:delete"})
    @GetMapping("requiresPermissions")
    @ResponseBody
    public String requiresPermissions(){
        return "此权限成功";
    }





}
