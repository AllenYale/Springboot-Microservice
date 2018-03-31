package com.travischenn.commonweb.config.authentication;

import com.travischenn.commonweb.config.handler.AuthenticationFailedHandler;
import com.travischenn.commonweb.config.handler.AuthenticationSuccessHandler;
import com.travischenn.commonweb.config.handler.LoginOutSuccessHandler;
import com.travischenn.commonweb.web.filter.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsUtils;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /** 成功登录处理器 */
    @Autowired
    private AuthenticationSuccessHandler travisChennAuthenticationSuccessHandler;

    /** 失败处理处理器 */
    @Autowired
    private AuthenticationFailedHandler travisChennAuthenticationFailedHandler;

    /** 登出成功处理器 */
    @Autowired
    private LoginOutSuccessHandler loginOutSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 跨域处理
        List<Header> headerList = new ArrayList<>(); // 请求报文请求头列表
        headerList.add(new Header("Access-Control-Allow-Origin", "*"));
        headerList.add(new Header("Access-Control-Allow-Methods", "POST, GET, OPTIONS,DELETE,PUT"));
        headerList.add(new Header("Access-Control-Allow-Headers", "*"));
        http.headers().addHeaderWriter(new StaticHeadersWriter(headerList));

        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.formLogin()
                .loginProcessingUrl("/api/authentication/token")
                .successHandler(travisChennAuthenticationSuccessHandler)
                .failureHandler(travisChennAuthenticationFailedHandler)
                .and()
                .logout()
                .logoutSuccessHandler(loginOutSuccessHandler)
                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/api/authentication/token","/api/dept/structure").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();

    }

}
