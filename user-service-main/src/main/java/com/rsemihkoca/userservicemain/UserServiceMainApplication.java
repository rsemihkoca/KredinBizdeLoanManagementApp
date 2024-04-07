package com.rsemihkoca.userservicemain;

import com.rsemihkoca.userservicemain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@AllArgsConstructor
public class UserServiceMainApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceMainApplication.class, args);
	}

	private final UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		userRepository.initializeDatabase();
		System.out.println("Data Initialized...");
	}
}
