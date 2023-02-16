package com.douzone.mysite.config;

import org.springframework.context.annotation.Import;

import com.douzone.mysite.config.app.DBConfig;
import com.douzone.mysite.config.app.MyBatisConfig;

@Import({DBConfig.class,MyBatisConfig.class})
public class AppConfig {

}
