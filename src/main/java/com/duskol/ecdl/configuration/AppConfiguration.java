package com.duskol.ecdl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
//@EnableWebMvc
//@ComponentScan
//@EnableWebSecurity
public class AppConfiguration implements WebMvcConfigurer { //implements WebMvcConfigurer {//extends WebSecurityConfigurerAdapter {
	
	/*
	@Override
	public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
    }*/
	
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/ecdl/**")
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedOrigins("*")
				.allowedHeaders("*");
			}
		};
	}
	
}
