package com.ncu.college.configurationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigurationserverApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ConfigurationserverApplication.class);

		// Default to native (classpath) config unless CONFIG_MODE is explicitly set to 'git'.
		String mode = System.getenv("CONFIG_MODE");
		if ("git".equalsIgnoreCase(mode)) {
			app.setAdditionalProfiles("git");
		} else {
			app.setAdditionalProfiles("native");
		}
		app.run(args);
	}
}
