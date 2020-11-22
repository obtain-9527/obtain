package com.obtain.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author obtain_y
 * @date 2020/11/22 22:50
 * @description com.obtain.study.comfig
 */

//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.requestMatchers().antMatchers("/oauth/**")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/oauth/**").authenticated();
//    }
//
//    //配置内存模式的用户
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("demoUser1").password(passwordEncoder()
//                .encode("123456")).authorities("USER").build());
//        manager.createUser(User.withUsername("demoUser2").password(passwordEncoder()
//                .encode("123456")).authorities("USER").build());
//        return manager;
//    }
//
//    /**
//     * 一定要将 userDetailsService 设置到 AuthenticationManagerBuilder 中
//     * 不然后面校验ClientDetailsService时会找不到UsernamePasswordToken的Provider
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService());
//    }
//
//    /**
//     * 需要配置这个支持password模式
//     * support password grant type
//     */
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
}
