package com.douzone.mysite.config.web;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageConfig {
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.addBasenames("com/douzone/mysite/web/mcessages/messages_ko","com/douzone/mysite/web/messages/messages_en");
		messageSource.setDefaultEncoding("utf-8");
		return messageSource;		
	}
}
