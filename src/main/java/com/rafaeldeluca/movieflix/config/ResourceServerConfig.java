package com.rafaeldeluca.movieflix.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	private static String [] PUBLIC = {"/h2-console/**", "/oauth/token"};
	
	private static String [] MEMBER_OR_VISITOR = {"/users/profile/**", "/reviews/**"};
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private JwtTokenStore tokenStore;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		if (Arrays.asList(environment.getActiveProfiles()).contains("test")==true) {
			http.headers().frameOptions().disable();
		}
		
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll()
		.antMatchers(HttpMethod.POST,PUBLIC).permitAll()
		//.antMatchers(MEMBER_OR_VISITOR).permitAll()
		.antMatchers(HttpMethod.PUT).hasRole("MEMBER")
		.antMatchers(HttpMethod.DELETE).hasRole("MEMBER")
		.antMatchers(HttpMethod.POST).hasRole("MEMBER")
		.anyRequest().authenticated();
	}	
}