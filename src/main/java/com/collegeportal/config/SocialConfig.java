package com.collegeportal.config;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import com.collegeportal.dao.UserDao;
import com.collegeportal.security.service.SecurityUserDetailServiceImpl;
import com.collegeportal.security.service.SocialConnectionSignupService;

@Configuration
@EnableSocial
@PropertySource({"classpath:properties/social-config.properties"})
public class SocialConfig implements SocialConfigurer {
	
	private static final Logger logger = LogManager.getLogger(SecurityUserDetailServiceImpl.class);
	
	@Autowired
	private DataSource dataSource;

	@Autowired
    private Environment env;
	
	private UserDao userDao;
	
	//TO stop the circular dependency in Spring bean creation
	@Autowired
	public SocialConfig(@Lazy UserDao userDao){
		this.userDao = userDao;
	}
	
	@Bean
	public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator,ConnectionRepository connectionRepository) {
		ConnectController cc = new ConnectController(connectionFactoryLocator, connectionRepository);
	    return cc;
	}
	
	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer conFacConfig, Environment env) {
	    
		//Google 
		GoogleConnectionFactory gfactory = new GoogleConnectionFactory(env.getProperty("google.client.id"),env.getProperty("google.client.secret"));
	    logger.info("Google OAuth API Scope : "+env.getProperty("google.scope"));
	    gfactory.setScope(env.getProperty("google.scope"));
	      
	    //Facebook
	    FacebookConnectionFactory ffactory = new FacebookConnectionFactory(env.getProperty("facebook.app.id"),env.getProperty("facebook.app.secret"));
	    ffactory.setScope(env.getProperty("facebook.scope"));
	    conFacConfig.addConnectionFactory(ffactory);
	      
	    conFacConfig.addConnectionFactory(gfactory);
	}

	@Override
	public UserIdSource getUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
	      JdbcUsersConnectionRepository usersConnectionRepository = new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator,Encryptors.noOpText());
	 
	      String autoSingUp = env.getProperty("autoSignUp");
	      if (autoSingUp != null && autoSingUp.equals("true")) {
	          // Automatically create corresponding USER_ACCOUNT if not already.
	          ConnectionSignUp connectionSignUp = new SocialConnectionSignupService(userDao);
	          usersConnectionRepository.setConnectionSignUp(connectionSignUp);
	      } else {
	          // If USER_ACCOUNTS does not exists
	          // Redirect to register page.
	          usersConnectionRepository.setConnectionSignUp(null);
	      }
	      return usersConnectionRepository;
	}

}
