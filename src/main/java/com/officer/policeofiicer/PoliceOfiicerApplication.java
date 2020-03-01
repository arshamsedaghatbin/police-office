package com.officer.policeofiicer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableFeignClients
public class PoliceOfiicerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoliceOfiicerApplication.class, args);
	}

}
