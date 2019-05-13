package com.example.springdata;

import com.example.springdata.config.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
public class SpringDataApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(ApplicationConfig.class);
	}
}
