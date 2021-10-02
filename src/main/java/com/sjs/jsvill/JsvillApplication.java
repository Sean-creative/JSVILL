package com.sjs.jsvill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JsvillApplication {

	public static void main(String[] args) {

		SpringApplication.run(JsvillApplication.class, args);

	}

}
