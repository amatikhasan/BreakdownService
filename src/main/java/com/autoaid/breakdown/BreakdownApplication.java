package com.autoaid.breakdown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BreakdownApplication {

	public static void main(String[] args) {
		SpringApplication.run(BreakdownApplication.class, args);
	}

}
