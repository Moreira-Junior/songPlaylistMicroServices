package com.MoreiraJunior.cloud.temafinal2.playlistservice;

import com.MoreiraJunior.cloud.temafinal2.playlistservice.config.AppConfig;
import com.netflix.discovery.EurekaClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class PlaylistServiceApplication {

	private static ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(PlaylistServiceApplication.class, args);
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		EurekaClient client = context.getBean(EurekaClient.class);
	}

}
