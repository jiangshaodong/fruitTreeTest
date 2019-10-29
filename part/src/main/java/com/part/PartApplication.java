package com.part;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("com.part.controller")
@ComponentScan("com.part.service")
@MapperScan("com.part.dao")
@EnableTransactionManagement
@ServletComponentScan
@EnableScheduling
public class PartApplication {
	public static void main(String[] args) {
		SpringApplication.run(PartApplication.class, args);
		System.err.println("swagger:http://localhost:8000/swagger-ui.html");
		System.err.println("admin:http://localhost:8000/ui/index");
	}
}
