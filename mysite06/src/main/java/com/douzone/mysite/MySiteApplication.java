package com.douzone.mysite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.event.ApplicationContextEventListener;

@SpringBootApplication
public class MySiteApplication {

	// ApplicationContextEventListener
	@Bean
	public ApplicationContextEventListener applicationContextEventListener() {
		return new ApplicationContextEventListener();
	}

	@Controller
	public class TestController {
		@ResponseBody
		@RequestMapping("/test")
		public String test() {
			return "Hello World";
		}
	}

	public static void main(String[] args) {
		// 웹에서는 톰캣이 실행하면서 쓰레드가 뜨기 때문에 try로 종료시키면 안됨.
		SpringApplication.run(MySiteApplication.class, args);

	}

}
