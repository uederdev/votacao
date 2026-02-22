package br.com.ueder.votacao_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StartApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApiApplication.class, args);
	}

}
