package com.sjs.jsvill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//자동으로 시간처리를 함
@EnableJpaAuditing //JPA를 이용하면서 AuditionEntityListener를 활성
public class JsvillApplication {
	public static void main(String[] args) {
		SpringApplication.run(JsvillApplication.class, args);
	}
}
