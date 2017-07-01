package com.wisely;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by gaowenfeng on 2017/2/5.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/login").permitAll()  //Spring Security 对/和/login 路径不拦截
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")                    //设置Spring Security的登录访问的路径为/login
                .defaultSuccessUrl("/chat")             //登录成功后转向/chat路径
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    /**
     * 在内存中分配设置两个用户gwf和jd,密码和用户名一致,角色为USER
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("gwf").password("gwf").roles("USER")
                .and()
                .withUser("jd").password("jd").roles("USER");
    }

    /**
     * /resources/static/下的静态资源,Spring Security不拦截
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/static/**");
    }
}
