package com.MoreiraJunior.cloud.temafinal2.appservice;

import com.MoreiraJunior.cloud.temafinal2.appservice.config.AppConfig;
import com.netflix.discovery.EurekaClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class AppServiceApplication {

	private static ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(AppServiceApplication.class, args);
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		EurekaClient client = context.getBean(EurekaClient.class);
	}

}
