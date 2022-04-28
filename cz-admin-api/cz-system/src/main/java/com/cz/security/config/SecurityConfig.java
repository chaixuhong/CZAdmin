package com.cz.security.config;

import com.cz.security.filter.CustomUsernameAndPasswordAuthFilter;
import com.cz.security.filter.TokenAuthenticationFilter;
import com.cz.security.service.UserDetailsServiceImpl;
import com.cz.security.filter.CodeAuthenticationFilter;
import com.cz.security.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private CustomAuthFailureHandler customAuthFailureHandler;

    @Autowired
    private CustomAuthSuccessHandler customAuthSuccessHandler;

    @Autowired
    private CustomAuthExpiredHandler customAuthExpiredHandler;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //注入自定义的用户加载类
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public CodeAuthenticationFilter codeAuthenticationFilter() {
        return new CodeAuthenticationFilter();
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }


    @Bean
    public CustomUsernameAndPasswordAuthFilter customUsernameAndPasswordAuthFilter() {
        CustomUsernameAndPasswordAuthFilter customUsernameAndPasswordAuthFilter = new CustomUsernameAndPasswordAuthFilter();
        customUsernameAndPasswordAuthFilter.setAuthenticationFailureHandler(customAuthFailureHandler);
        customUsernameAndPasswordAuthFilter.setAuthenticationSuccessHandler(customAuthSuccessHandler);
        return customUsernameAndPasswordAuthFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                //登录
                .antMatchers("/login", "/auth/code").permitAll()
                // 静态资源
                .antMatchers(HttpMethod.GET, "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/webSocket/**", "/favicon.ico").permitAll()
                //swagger
                .antMatchers("/swagger-resources/**", "/webjars/**", "/v2/api-docs").permitAll()
                // 文件
                .antMatchers("/avatar/**", "/file/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(customAuthExpiredHandler)
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .logout()
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(customUsernameAndPasswordAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(codeAuthenticationFilter(), CustomUsernameAndPasswordAuthFilter.class);

    }
}
