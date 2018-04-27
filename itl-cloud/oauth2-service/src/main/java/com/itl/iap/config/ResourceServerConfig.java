package com.itl.iap.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;



/**
 * Created by Joeysin on 2017/6/9.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
            .and()
                .authorizeRequests().antMatchers("/noAuth").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/demo").permitAll()
                .antMatchers("/oauth/*").permitAll()
                .anyRequest().authenticated()
             .and()
                .httpBasic();
           ;
      /*  http.authorizeRequests().antMatchers("/user/token").permitAll().antMatchers("/oauth/authorize").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/demo").permitAll()
                .anyRequest().authenticated()
                 .and()
                .httpBasic();*/
        // 解决不允许显示在iframe的问题
        http.headers().frameOptions().disable();
        http.headers().cacheControl();
    }

  /*  @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();



        http.authorizeRequests().antMatchers("/user/token").permitAll().antMatchers("/oauth/authorize").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/users").permitAll()
                .anyRequest().authenticated();
        http.formLogin().loginProcessingUrl("/user/token").successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);

        // 基于密码 等模式可以无session,不支持授权码模式
        if(authenticationEntryPoint!=null){
            http.exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint);
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        }else{
            //授权码模式单独处理，需要session的支持，此模式可以支持所有oauth2的认证
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        }


        // http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
        // 解决不允许显示在iframe的问题
        http.headers().frameOptions().disable();
        http.headers().cacheControl();

    }*/
}
