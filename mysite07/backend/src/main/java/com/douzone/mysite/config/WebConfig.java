package com.douzone.mysite.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.douzone.mysite.security.AuthInterceptor;

@SpringBootConfiguration
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public HandlerInterceptor authInterceptor() {
		return new AuthInterceptor();
	}

	// argument resolver
//	@Bean
//	public HandlerMethodArgumentResolver handlerMethodArgumentResolver() {
//		return new AuthUserHandlerMethodArgumentResolver();
//	}

//	@Override
//	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//		resolvers.add(handlerMethodArgumentResolver());
//	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/login",
				"/user/logout", "/asserts/**");
	}


}
