package com.yong.resource.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiangYong
 * @createdDate 2017/11/11
 */

@Configuration
@EnableOAuth2Client
@EnableResourceServer
@ConfigurationProperties(prefix = "yong.unprotected")
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private List<String> urls = new ArrayList<>();

	public List<String> getUrls() {
		return urls;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		//TODO use config protected-urls instead of hard code
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/*.html", "/**/*.css", "/**/*.js", "/**/*.png").permitAll()
//				.antMatchers(urls.stream().toArray(String[]::new)).permitAll()
				.regexMatchers(urls.stream().toArray(String[]::new)).permitAll()
				.anyRequest().authenticated().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.antMatcher("/**").authorizeRequests().anyRequest().permitAll();
	}

	@Bean
	public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext, OAuth2ProtectedResourceDetails details) {
		return new OAuth2RestTemplate(details, oauth2ClientContext);
	}

}
