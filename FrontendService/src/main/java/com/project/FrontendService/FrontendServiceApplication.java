package com.project.FrontendService;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Log4j2
@EnableFeignClients
public class FrontendServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontendServiceApplication.class, args);
		log.info("FRONTEND SERVICE IS RUNNING...");
	}



	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
