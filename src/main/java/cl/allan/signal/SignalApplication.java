package cl.allan.signal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Signal application.
 * This class initializes the Spring Boot context and manages the distribution of signals
 * across the system architecture.
 */
@SpringBootApplication
public class SignalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SignalApplication.class, args);
	}

}
