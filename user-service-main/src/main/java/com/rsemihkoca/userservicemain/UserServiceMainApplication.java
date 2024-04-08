package com.rsemihkoca.userservicemain;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@AllArgsConstructor
public class UserServiceMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceMainApplication.class, args);
	}

}
