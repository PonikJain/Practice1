package com.collegeportal.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/*
 * This Class is Spring Application Context and used in respect to dispatcher-servlet.xml 
 * So we don't need that xml any more
 * */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.collegeportal.*"})
public class WebApplicationContext extends WebMvcConfigurerAdapter{
	private static final Logger logger = LogManager.getLogger(WebApplicationContext.class);
	
	/*
	 * View Resolver to resolve the JSP view
	 * */
	@Bean
    public ViewResolver viewResolver() {
		logger.info("Spring View Resolve Context loaded....");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        // Needed to resolve JSP's jstl tags otherwise it won't be able to resolve
        viewResolver.setViewClass(JstlView.class); 
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
	
	/*
	 * Useful the case of static element serving like image and css and other assets
	 * As we don't map the assets to url mapping to it get resolved by this
	 * */
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
	/*
	 * We don't need it As we are loading all the static component through the default 
	 * Servlet Handler and if we have some custom reqirement to get the static content from file 
	 * or folder then we need it otherwise not
	 * */
	/* @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/resurces/**")
	        		.addResourceLocations("/WEB-INF/resourcses/")
	        	      .setCachePeriod(3600)
	        	      .resourceChain(true);
	    } */

}
