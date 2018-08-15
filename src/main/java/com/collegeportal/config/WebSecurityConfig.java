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
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.security.SpringSocialConfigurer;


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
        return new BCryptPasswordEncoder();
    }
	
	/*
	 * It will help to skip the authenticaion for few urls and resources
	 * */
	@Override
    public void configure(WebSecurity web) throws Exception {
      web.ignoring().antMatchers("/resources/**");
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		logger.info("--------********----------_*******CONFIGURE**********");
        http.authorizeRequests()
        .antMatchers("/login","/register").permitAll()
        .anyRequest().
        access("hasRole('USER')").
        and().
        formLogin().loginPage("/login").defaultSuccessUrl("/homePage").
        loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password").
        and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout").deleteCookies("JSESSIONID").
        and().
        csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied")
        .and()
        .apply(new SpringSocialConfigurer().signupUrl("/signup").postLoginUrl("/homePage"));
        
    }
	
	
}


