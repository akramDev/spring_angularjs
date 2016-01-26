package com.exemple.spring.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthSuccess authSuccess;
	
	@Autowired
	private AuthFailure authFailure;
	
	@Autowired
	private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;
	
	@Autowired
	public void configAuthBuilder(AuthenticationManagerBuilder builder) throws Exception{
		builder.inMemoryAuthentication().withUser("test").password("test").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().exceptionHandling().authenticationEntryPoint(entryPointUnauthorizedHandler)
			.and().formLogin().successHandler(authSuccess).failureHandler(authFailure).and()
			.authorizeRequests().antMatchers("/**").permitAll();
	}
	
	

}
