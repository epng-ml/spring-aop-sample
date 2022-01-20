package com.example.springaopsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringAopSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAopSampleApplication.class, args);
	}

}
