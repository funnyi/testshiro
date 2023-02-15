package com.study.boot.config;


import com.study.boot.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    @Autowired
    private MyRealm realm;

    @Bean
    public DefaultWebSecurityManager defaultSecurityManager(){
        //创建defaultWebSecurityManager 对象
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //创建加密对象。设置相关属性
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //说明采用MD5加密
        matcher.setHashAlgorithmName("md5");
        //说明加密迭代次数
        matcher.setHashIterations(3);
        //将加密对象存储到myRealm中
        realm.setCredentialsMatcher(matcher);
        //将myRealm存入defaultWebSecurityManager对象
        manager.setRealm(realm);
        manager.setRememberMeManager(rememberMeManager());
        //返回
        return manager;
    }



    //cookie属性设置
    public SimpleCookie rememberMeCookie(){
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //设置跨域
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30 * 24 * 60 * 60);
        return cookie;
    }

    //创建Shiro的Cookie管理对象
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey("1234567890987654".getBytes());
        return cookieRememberMeManager;
    }



    @Bean
    //配置Shiro内置拦截器拦截范围
    public DefaultShiroFilterChainDefinition defaultShiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        //设置不认证可以访问的资源
        definition.addPathDefinition("/myController/userLogin","anon");
        definition.addPathDefinition("/myController","anon");
//        definition.addPathDefinition("/myController/login","anon");
        definition.addPathDefinition("/myController/userLogout","logout");
        //设置需要进行登录认证的拦截范围
        definition.addPathDefinition("/**","authc");

        //rememberMe后才能访问的资源路径
        definition.addPathDefinition("/**","user");
        return definition;
    }
}
