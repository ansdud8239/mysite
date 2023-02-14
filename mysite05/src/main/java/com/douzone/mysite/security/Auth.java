package com.douzone.mysite.security;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({METHOD,TYPE})
public @interface Auth {
	//public String value() default "USER";
	public String role() default "USER";
	public boolean test() default false;
	
}
