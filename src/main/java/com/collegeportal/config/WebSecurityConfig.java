package com.collegeportal.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LogManager.getLogger(WebSecurityConfig.class);
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(PasswordEncoder());
        return authenticationProvider;
    }
	
	@Bean
    public BCryptPasswordEncoder PasswordEncoder() {
		logger.info("--------********----------_**********PASS*******");
        return new BCryptPasswordEncoder();
    }
	
	/*
	 * It will hwlp to skip the authenticaion for few urls and resources
	 * */
	@Override
    public void configure(WebSecurity web) throws Exception {
      web.ignoring().antMatchers("/resources/**");
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		logger.info("--------********----------_*******CONFIGURE**********");
        http.authorizeRequests()
        .antMatchers("/login").permitAll()
        .anyRequest().
        access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')").
        and().
        formLogin().loginPage("/login").defaultSuccessUrl("/homePage").
        loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password").
        and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout").and().
        csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied");
    }
	
	
}


