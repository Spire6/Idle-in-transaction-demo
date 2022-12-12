package hu.example.idleintransactiondemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableTransactionManagement
public class IdleInTransactionDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdleInTransactionDemoApplication.class, args);
	}

}
