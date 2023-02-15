package com.study.boot;

import com.study.boot.util.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestshiroApplicationTests {

    @Test
    void contextLoads() {
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //获取Subject对象
        Subject subject = SecurityUtils.getSubject();

        //创建token对象，web应用用户名密码从页面传递
        AuthenticationToken token = new UsernamePasswordToken("zhangsan","z3");

        //完成登录
        try {
            subject.login(token);
            System.out.println("登录成功");
            boolean hasRole = subject.hasRole("role1");
            System.out.println("是否拥有此角色" + hasRole);
            boolean permitted = subject.isPermitted("user:select");
            System.out.println(permitted);
        }catch (UnknownAccountException e){
            e.printStackTrace();
        } catch (AuthenticationException e){
            String message = e.getMessage();
            if(message != null){
                message = "用户名或者密码不正确";
            }
            System.out.println(message);
            e.printStackTrace();
        }
    }


    @Test
    public void MD5Test(){
        System.out.println(MD5Utils.MD5HAsh("z3"));
        System.out.println(MD5Utils.MD5HAsh("ls"));
    }


    @Test
    public void CharTest(){
        char[] chars = {'1','2','3'};
        String s = new String(chars);
        System.out.println(s);
    }

}
