package it.univaq.disim.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DiscoveryClientModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryClientModuleApplication.class, args);
	}

}
