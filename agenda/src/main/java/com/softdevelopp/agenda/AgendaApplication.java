package com.softdevelopp.agenda;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.dialect.springdata.SpringDataDialect;



@Slf4j
@SpringBootApplication

public class AgendaApplication {


	public AgendaApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(AgendaApplication.class, args);
	}

	@Bean
	public SpringDataDialect springDataDialect() {
		return new SpringDataDialect();
	}

	public static org.slf4j.Logger getLog() {
		return log;
	}

}
