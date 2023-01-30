package com.check.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * 其中@EnableWebSecurity 表示是Spring Security的配置类
 * 其中@EnableGlobalMethodSecurity(prePostEnabled = true)表示开启@PreAuthorize、@PostAuthorize, @Secured这三个注解支持
 * 4、定义Spring Security配置类
 * 开启注解权限控制
 *
 * @author zzc
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;

    @Resource
    private LoginFailureHandler loginFailureHandler;

    @Resource
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Resource
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .successHandler(jwtAuthenticationSuccessHandler)
                .failureHandler(loginFailureHandler)
                .loginProcessingUrl("/login")
                //关闭csrf
                .and()
                .csrf().disable()
                // 自定义认证失败类
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                // 自定义权限不足处理类
                .accessDeniedHandler(jwtAccessDeniedHandler).and()
                //设置无状态登录
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //过滤登录退出请求
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/doc.html").permitAll()
                .antMatchers("/doc.html/**").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                //加油站查询不用登录
                .antMatchers("/gasStation/list").permitAll()
                //所有请求都需要拦截
                .anyRequest()
                .authenticated();
        //添加token验证过期器，并指定过滤器的类型是UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/css/**",
                "/js/**",
                //首页
                "index.html",
                //图标
                "favicon.ico",
                //swagger2
                "/doc.html",
                //下面swagger2需要的一些资源
                "/webjars/**",
                "/swagger-resources/**",
                "/v2/api-docs/**"
        );
    }
}
